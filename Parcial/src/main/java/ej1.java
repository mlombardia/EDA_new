public class ej1 {

    static public boolean sorpresaV3(int[] arreglo, int n) {
        if (arreglo == null || n < 0 || arreglo.length < n)
            throw new RuntimeException("bad parameter");

        mergeSort(arreglo,n);

        for (int rec = 0; rec <= n - 1; rec++)
            for (int iter = 0; iter <= n - 1; iter++)
                if (rec != iter && arreglo[rec] == arreglo[iter])
                    return false;

        return true;
    }

    public static void mergeSort(int[] array, int n) {
        if (n < 2) {
            return;
        }

        int mid = n / 2;
        int[] left_array = new int[mid];
        int[] right_array = new int[n - mid];

        //Divido al array en dos y copio en dos partes separadas
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (i < mid) {
                left_array[i] = array[i];
            } else {
                right_array[k] = array[i];
                k = k + 1;
            }
        }
        //Hago llamada recursiva a los sub-items que vienen despues

        mergeSort(left_array,n);
        mergeSort(right_array,n-mid);
        //Hago el merge en cada subidivision
        merge(left_array, right_array, array);
    }

    private static void merge(int[] left_array, int[] right_array, int[] array) {
        int left_len = left_array.length;
        int right_len = right_array.length;
        int i = 0, l = 0, r = 0;

        //El while este chequea las condiciones para mergear
        while (l < left_len && r < right_len) {
            if (left_array[l] < right_array[r]) {
                array[i++] = left_array[l++];
            } else {
                array[i++] = right_array[r++];
            }
        }
        while (l < left_len) {
            array[i++] = left_array[l++];
        }
        while (r < right_len) {
            array[i++] = right_array[r++];
        }
    }
}

//En este caso se eligio hacer el sorting con mergesort, que tiene compl O(n*logn) y que sumado a la iteracion,
//que es O(n), sigue siendo O(n*logn) porque O(n) < O(n*logn) y el de SopresaV3 seria O(n)+O(n*logn)
//La complejidad espacial tambien es O(n*log(n)) por el mergesort