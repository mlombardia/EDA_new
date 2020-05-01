public class AlgoA {

    public static int max(int[] array) {
        if (array == null || array.length == 0) //comparacion, dos fijas
            throw new RuntimeException("Empty array");

        int candidate = array[0];
        for (int rec = 1; rec < array.length - 1; rec++) //tengo un for, n veces
            if ( candidate < array[rec] ) //(comparacion del for + suma for + comparacion de adentro)*n
                candidate = array[rec];
                                            //3n
        return candidate;
    }
    //cant 2+3n
}