public class IndexParametricTester {
    public static void main(String args[]){
        IndexParametricWithDuplicates myIndex = new IndexParametricWithDuplicates(Integer.class);

        try {
            myIndex.initialize(new Integer[]{10, 10, 10, 20, 20, 20, 30, 30, 30, 40, 40});
        } catch (Exception var3) {
            ;
        }

        myIndex.sortedPrint();
        Integer[] rta = (Integer[])myIndex.range(10, 40, false, false);
        printRange(rta);

        myIndex.sortedPrint();
        rta = (Integer[])myIndex.range(10, 40, true, true);
        printRange(rta);

        myIndex.sortedPrint();
        rta = (Integer[])myIndex.range(10, 40, true, false);
        printRange(rta);

        myIndex.sortedPrint();
        rta = (Integer[])myIndex.range(10, 40, false, true);
        printRange(rta);

        myIndex.sortedPrint();
        rta = (Integer[])myIndex.range(2, 40, false, false);
        printRange(rta);

        myIndex.sortedPrint();
        rta = (Integer[])myIndex.range(2, 40, true, true);
        printRange(rta);

        myIndex.sortedPrint();
        rta = (Integer[])myIndex.range(2, 40, true, false);
        printRange(rta);

        myIndex.sortedPrint();
        rta = (Integer[])myIndex.range(2, 40, false, true);
        printRange(rta);

        myIndex.sortedPrint();
        rta = (Integer[])myIndex.range(10, 50, false, false);
        printRange(rta);

        myIndex.sortedPrint();
        rta = (Integer[])myIndex.range(10, 50, true, true);
        printRange(rta);

        myIndex.sortedPrint();
        rta = (Integer[])myIndex.range(10, 50, false, true);
        printRange(rta);

        myIndex.sortedPrint();
        rta = (Integer[])myIndex.range(10, 50, true, false);
        printRange(rta);

        myIndex.sortedPrint();
        rta = (Integer[])myIndex.range(5, 12, false, false);
        printRange(rta);

        myIndex.sortedPrint();
        rta = (Integer[])myIndex.range(5, 12, true, true);
        printRange(rta);

        myIndex.sortedPrint();
        rta = (Integer[])myIndex.range(5, 12, false, true);
        printRange(rta);

        myIndex.sortedPrint();
        rta = (Integer[])myIndex.range(5, 12, true, false);
        printRange(rta);
    }

    private static void printRange(Integer[] rta){
        System.out.print("[" + rta[0]);
        for(int i=1; i < rta.length; i++){
            System.out.print(", " + rta[i]);
        }
        System.out.println("]");
    }
}
