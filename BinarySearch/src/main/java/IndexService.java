public interface IndexService {

    // elements seran los valores del indice, los anteriores se descartan,
    // lanza exception si elements is null y deja los valores anteriores
    void initialize(int [] elements) throws Exception;

    // busca key en el indice, O(log2n)
    boolean search(int key);

    // inserta el key en la pos correcta. Crece automaticamente de a chunks
    void insert(int key);

    // borra el key si lo hay, sino la ignora
    // decrece automaticamente de a chunks
    void delete(int key);

    // devuelve la cantidad de apariciones de la clave especificada
    int occurrences(int key);

    // devuelve un nuevo arreglo ordenado con los elementos que pertenecen al intervalo dado por
    // leftkey y rightkey.  Si el mismo es abierto/cerrado depende de las variables leftIncluded
    // y rightIncluded. True indica que es cerrado. Si no hay matching devuelve arreglo de length 0
    int[] range(int leftKey, int rightKey, boolean leftIncluded, boolean rightIncluded);


    // imprime el contenido del índice ordenado por su key.
    void sortedPrint();


    // devuelve el máximo elemento del índice. Lanza RuntimeException si no hay elementos
    int getMax();

    // devuelve el mínimo elemento del índice. Lanza RuntimeException si no hay elementos
    int getMin();

}
