import java.util.Arrays;

public class AlgoB {

    public static int max (int[] array) {
        if (array == null || array.length == 0) //2
            throw new RuntimeException("Empty array");

        Arrays.sort(array);  // ordena ascendentemente nlog(n)

        return array[array.length - 1];
    }
                    //2+nlog(n)
}

//regla de oro, la cte se descarta