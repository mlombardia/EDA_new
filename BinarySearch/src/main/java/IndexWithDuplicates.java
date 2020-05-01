public class IndexWithDuplicates implements IndexService {

    private static final int CHUNK = 10;
    public int[] index;
    public int dim;
    public IndexWithDuplicates(){
        reset();
    }

    public void initialize(int[] elements) throws Exception {
        if(elements==null){
            throw new Exception();
        }

        reset();
        for(int i=0;i<elements.length;i++)
            insert(elements[i]);
    }

    public boolean search(int key) {
        if(index==null){
            return false;
        }
        int dim = index.length;
        int result = binarySearch(key,0,dim-1);
        return result != -1;
    }

    public void insert(int key) {
        if(dim == index.length){
            int[] aux = new int[dim+CHUNK];
            System.arraycopy(index,0,aux,0,index.length);
            index=aux;
        }
        int pos = getClosestPosition(key);
        if(dim !=0 && (key>index[pos] && index[pos] != 0))
            pos+=1;
        if(pos<dim){
            for(int i=dim;i >= pos+1;i--){
                index[i] = index[i-1];
            }
        }
        index[pos] = key;
        dim++;
    }

    public void delete(int key) {
        if(contains(key)){
            int pos = binarySearch(key,0,dim);
            if(pos==dim){
                index[pos]=0;
            }else {
                for (int i = pos + 1; i < dim; i++) {
                    index[i - 1] = index[i];
                }
            }
            dim--;
        }
    }

    public int occurrences(int key) {
        if(index==null){
            return 0;
        }
        int count = 0;
        for(int i = 0;i<index.length;i++){
            if(index[i]>key)
                break;
            if(index[i] == key){
                count++;
            }
        }
        return count;
    }

    public int[] range(int leftKey, int rightKey, boolean leftIncluded, boolean rightIncluded) {
        int[] range=new int[CHUNK];
        int dim_range=0;
        int leftPos=getClosestPosition(leftKey);
        int rightPos=getClosestPosition(rightKey);
        int i=leftPos,j=rightPos;
        if(leftIncluded){
            while(i>0 && index[i]>=leftKey && isInsideRange(leftKey)){
                    i--;
            }
            if(i!=0){
                i++;
            }
        }else {
            while(i<dim-1 && index[i]<=leftKey && isInsideRange(leftKey)){
                    i++;
            }
        }
        leftPos=i;
        if(rightIncluded){
            while(j<dim-1 && index[j]<=rightKey && isInsideRange(rightKey)){
                    j++;
            }
            j--;
        }else {
            while(j>0 && index[j]>=rightKey && isInsideRange(rightKey)){
                    j--;
            }
        }
        rightPos=j;
        if(leftPos<=rightPos && (isInsideRange(leftKey) || isInsideRange(rightKey))) {
            for (int iter = leftPos; iter <= rightPos; iter++) {
                range[dim_range] = index[iter];
                dim_range++;
            }
        }
        return range;
    }

    public void sortedPrint() {
        if(!isEmpty()){
            System.out.print("[" + index[0]);
            for(int i=1; i < dim; i++){
                System.out.print(", " + index[i]);
            }
            System.out.println("]");
        }else{
            System.out.println("[]");
        }
    }

    public int getMax() {
        if(isEmpty())
            throw new RuntimeException();
        return index[dim-1];
    }

    public int getMin() {
        if(isEmpty())
            throw new RuntimeException();
        return index[0];
    }

    private boolean contains(int key){
        for (int i=0;i<index.length;i++){
            if (index[i]==key)
                return true;

        }
        return false;
    }

    public int printClosestPosition(int key){
        return getClosestPosition(key);
    }

    private int getClosestPosition(int key){
        int left=0;
        int right=dim-1;
        return searchClosestPosition(key,left,right,0);
    }

    private int searchClosestPosition(int key, int left, int right,int control){
        int pos=control;
        if(left<=right){
            pos = (left+right)/2;
            if(index[pos] == key)
                return pos;
            if(key<index[pos])
                return searchClosestPosition(key,left,pos-1,pos);
            else
                return searchClosestPosition(key, pos + 1, right,pos);
        }
        return pos;
    }

    private int binarySearch(int key, int left, int right){
        if(left <= right){
            int mid = (left+right)/2;
            if(index[mid] == key)
                return mid;
            if(key<index[mid])
                return binarySearch(key,left,mid-1);
            else
                return binarySearch(key,mid + 1,right);
        }
        return -1;
    }

    private void reset(){
        index = new int[CHUNK];
        dim = 0;
    }

    private boolean isEmpty(){
        for(int i=0;i < index.length;i++){
            if(index[i]!=0)
                return false;
        }
        return true;
    }

    private boolean isInsideRange(int key){
        if(key>index[dim-1]){
            return false;
        }
        if(key<index[0]){
            return false;
        }
        return true;
    }
}