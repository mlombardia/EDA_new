import java.lang.reflect.Array;
import java.util.Arrays;

public class IndexParametricWithDuplicates<T extends Comparable<? super T>> implements IndexParametricService<T>{

    private static final int CHUNK = 10;
    private T[] index;
    private int dim;

    public IndexParametricWithDuplicates(Class<? extends T> class1){
        dim=0;
        this.index = (T[]) Array.newInstance(class1,CHUNK);
    }

    public void initialize(T[] elements) {
        if(elements == null){
            throw new RuntimeException();
        }
        Arrays.sort(elements);
        int new_length = CHUNK * (1 + elements.length / CHUNK);
        index = (T[])Arrays.copyOf(elements,new_length);
        index = elements;
        dim=elements.length;
    }

    public boolean search(T key) {
        return binarySearch(key,0,dim-1) != -1;
    }

    private int binarySearch(T key, int left, int right){
        if(left<=right){
            int mid=(left+right)/2;
            if(index[mid].compareTo(key)==0)
                return mid;
            if(key.compareTo(index[mid])<0)
                return binarySearch(key, left, mid-1);
            else
                return binarySearch(key,mid+1,right);
        }
        return -1;
    }

    public void insert(T key) {
        if(dim == index.length){
            index = (T[])Arrays.copyOf(index, dim+CHUNK);
        }

        int pos = getClosestPosition(key);

        for(int i = dim; i > pos; i--) {
            index[i] = index[i-1];
        }

        index[pos]=key;
        dim++;
    }

    private int getClosestPosition(T key){
        int left=0;
        int right=dim-1;
        while(left<=right){
            int mid=(left+right)/2;
            if(key.compareTo(index[mid]) == 0)
                return mid;
            if(key.compareTo(index[mid])<0)
                right=mid-1;
            else
                left=mid+1;
        }
        return left;
    }

    public void delete(T key) {
        int pos = binarySearch(key,0,dim-1);
        if(pos != -1){
            dim--;
            for(int i = pos; i < dim; i++) {
                index[i] = index[i+1];
            }
            if (index.length - this.dim > CHUNK) {
                index = (T[])Arrays.copyOf(index, index.length - CHUNK);
            }
        }
    }

    public int occurrences(T key) {
        int pos = binarySearch(key,0,dim-1);
        if(pos == -1){
            return 0;
        }else{
           int cont=1;

           int i;
           for(i=pos+1;pos<dim && index[i].compareTo(key)==0;i++){
                   cont++;
           }

            for(i=pos-1;pos >= 0 && index[i].compareTo(key)==0;i--){
                    cont++;
            }

           return cont;
        }
    }

    public T[] range(T leftKey, T rightKey, boolean leftIncluded, boolean rightIncluded) {
        if(dim != 0 && validateInterval(leftKey,rightKey,leftIncluded,rightIncluded)){
            if(rightIncluded){
                if(rightKey.compareTo(index[0])<0){
                    return (T[])Arrays.copyOfRange(index,0,0);
                }
            }else if(rightKey.compareTo(index[0])<=0){
                return (T[])Arrays.copyOfRange(index,0,0);
            }

            if (leftIncluded) {
                if (leftKey.compareTo(index[dim-1]) > 0) {
                    return (T[])Arrays.copyOfRange(index, 0, 0);
                }
            } else if (leftKey.compareTo(index[dim-1]) >= 0) {
                return (T[])Arrays.copyOfRange(index, 0, 0);
            }

            int leftPos = this.getClosestPosition(leftKey);
            if (leftIncluded) {
                if (leftKey.compareTo(index[leftPos]) == 0) {
                    while(leftPos - 1 >= 0 && leftKey.compareTo(index[leftPos-1]) == 0) {
                        leftPos--;
                    }
                }
            } else {
                while(leftPos + 1 < dim && leftKey.compareTo(index[leftPos]) == 0) {
                    leftPos++;
                }
            }

            int rightPos = this.getClosestPosition(rightKey);
            if (rightIncluded) {
                if (rightPos == dim) {
                    rightPos--;
                } else if (rightKey.compareTo(index[rightPos]) == 0) {
                    while(rightPos + 1 < dim && rightKey.compareTo(index[rightPos + 1]) == 0) {
                        rightPos++;
                    }
                } else {
                    rightPos--;
                }
            } else if (rightPos == dim) {
                rightPos--;
            } else if (rightKey.compareTo(index[rightPos]) == 0) {
                while(rightPos > 0 && rightKey.compareTo(index[rightPos]) == 0) {
                    rightPos--;
                }
            } else {
                rightPos--;
            }

            T[] auxi = (T[])Arrays.copyOfRange(index,leftPos,rightPos+1);
            return auxi;
        }else{
            return (T[])Arrays.copyOfRange(index,0,0);
        }
    }

    private boolean validateInterval(T leftKey, T rightKey, boolean leftIncluded, boolean rightIncluded) {
        if (leftKey.compareTo(rightKey) > 0) {
            return false;
        } else {
            return leftKey.compareTo(rightKey) != 0 || leftIncluded || rightIncluded;
        }
    }

    public void sortedPrint() {
        if(!(dim==0)){
            System.out.print("[" + index[0]);
            for(int i=1; i < dim; i++){
                System.out.print(", " + index[i]);
            }
            System.out.println("]");
        }else{
            System.out.println("[]");
        }
    }

    public T getMax() {
        if(dim==0)
            throw new RuntimeException();
        return index[dim-1];
    }

    public T getMin() {
        if(dim==0)
            throw new RuntimeException();
        return index[0];
    }
}
