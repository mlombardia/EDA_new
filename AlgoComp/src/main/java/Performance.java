
public class Performance {

    public static final int N = 600000000;

    public static void main(String[] args) {
        // generate array
        int[] myArray = new int[N];

        for (int rec = N; rec > 0; rec--)
            myArray[N - rec] = rec;

        // performance testing
        Timer t1 = new Timer();
        int rta1 = AlgoA.max(myArray);
        t1.stop();

        Timer t2 = new Timer();
        int rta2 = AlgoB.max(myArray);
        t2.stop();

        System.out.println(String.format("max Algo A %d. Delay %s (ms)", rta1, t1.toString()));
        System.out.println(String.format("max Algo B %d. Delay %s (ms)", rta2, t2.toString()));
    }

}

//AlgoA 2   7   1   74   108    207    353  324
//AlgoB 2   5   1   240  499    966    994  1049