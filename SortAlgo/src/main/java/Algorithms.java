public class Algorithms {
    public static void insertionSort(int[] array){
        for(int rec = 1; rec<array.length;rec++){
            int temp = array[rec];
            int pos = rec;
            for( ; pos>0 && array[pos-1]>temp;pos--){
                //hago lugar para el nuevo elem
                array[pos] = array[pos-1];
            }
            array[pos] = temp;
            //printArray(array);
        }
    }
//----------------------------------QUICKSORT--------------------------------------------------------------------------
    public static void quickSort(int[] array){
        quickSortHelper(array,0, array.length-1);
    }

    private static void quickSortHelper(int[] array, int min, int max){
        if(min >= max)
            return;

        //aca decidimos que el pivot sea el primer elem
        int pivot = array[min];
        swap(array,min,max);
        int middle = partition(array,min, max - 1, pivot);
        swap(array,middle,max); // el pivot al lugar correcto

        //sigo la recursion
        quickSortHelper(array,min,middle-1);
        quickSortHelper(array,middle+1,max);
    }

    private static int partition(int[] array, int left, int right, int pivot){
        while (left <= right){
            while (left <= right && array[left] < pivot){
                left++;
            }
            while (left <= right && array[right] > pivot){
                right--;
            }
            if (left <= right){
                swap(array,left++,right--);
            }
        }
        return left;
    }

    private static void swap(int[]data, int pos1, int pos2){
        int temp = data[pos1];
        data[pos1] = data[pos2];
        data[pos2] = temp;
    }
//---------------------------------------MERGESORT---------------------------------------------------------------------
    public static void mergeSort(int[] array){
        int len = array.length;
        if(len<2){
            return;
        }

        int mid = len/2;
        int[] left_array = new int[mid];
        int[] right_array = new int[len-mid];

        //Divido al array en dos y copio en dos partes separadas
        int k = 0;
        for(int i=0;i<len;i++){
            if(i<mid){
                left_array[i] = array[i];
            }
            else{
                right_array[k] = array[i];
                k = k+1;
            }
        }
        //Hago llamada recursiva a los sub-items que vienen despues
        mergeSort(left_array);
        mergeSort(right_array);
        //Hago el merge en cada subidivision
        merge(left_array,right_array,array);
    }

    private static void merge(int[] left_array,int[] right_array,int[] array){
        int left_len = left_array.length;
        int right_len = right_array.length;
        int i=0, l=0, r=0;

        //El while este chequea las condiciones para mergear
        while(l<left_len && r<right_len){
            if(left_array[l]<right_array[r]){
                array[i++] = left_array[l++];
            }else{
                array[i++] = right_array[r++];
            }
        }
        while (l<left_len){
            array[i++] = left_array[l++];
        }
        while (r<right_len){
            array[i++]  =right_array[r++];
        }
    }

    public static void printArray(int[] array){
        System.out.print("[ ");
        for(int i=0;i<array.length-1;i++){
            System.out.printf("%d, ",array[i]);
        }
        System.out.print(array[array.length-1]);
        System.out.println(" ]");
    }
}
