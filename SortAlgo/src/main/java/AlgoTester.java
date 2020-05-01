public class AlgoTester {
    public static void main (String[] args){
        int [] test = {0,8,1,12,45,16,21,84,21,98,100};
        printArray(test);
        Algorithms.mergeSort(test);
        printArray(test);
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
