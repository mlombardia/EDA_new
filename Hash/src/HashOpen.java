import java.util.LinkedList;
import java.util.function.Function;

public class HashOpen<K, V>
{
    // estatica. No crece. Espacio suficiente...
    //private Node<K,V>[] LookUp= new Node[initialLookupSize];

    private double threshold;
    private int dim;
    private int multiplier;

    private LinkedList<LinkedList<Node<K,V>>> LookUp= new LinkedList<>();

    private Function<? super K, Integer> prehash;

    public HashOpen( Function<? super K, Integer> mappingFn)
    {
        prehash= mappingFn;
        threshold = 0.75;
        multiplier = 1;
        reSize();
    }

    private void reSize(){
        for (int i = 10*(multiplier-1); i < 10*multiplier; i++){
            LookUp.add(new LinkedList<>());
        }
        multiplier++;
    }

    // ajuste al tamaño de la tabla
    private int hash(K key)
    {
        if (key == null)
            throw new RuntimeException("No key provided");

        return prehash.apply(key) % LookUp.size();
    }


    public V getValue(K key)
    {
        Node<K, V> entry = get(key);
        if (entry == null)
            return null;

        return entry.value;
    }

    private Node<K,V> get(K key)
    {
        int i = 0;

        if(!LookUp.get(hash(key)).isEmpty()) {
            if (!LookUp.get(hash(key)).get(i).key.equals(key)) {
                while (i < LookUp.get(hash(key)).size()
                        && !LookUp.get(hash(key)).get(i).key.equals(key)) {
                    i++;
                }
            }
            if (i >= LookUp.get(hash(key)).size())
                return null;

            return (LookUp.get(hash(key)).get(i).key.equals(key)?
                    LookUp.get(hash(key)).get(i) : null);
        }
        return null;
    }

    // insert = update
    public void insert(K key, V value)
    {
        /*if (LookUp.get(prehash.apply(key)) == null)
            LookUp.add(i, new LinkedList<>());*/

        /*if ((double)dim/LookUp.size() >= 0.75){
            reSize();
        }*/

        if (LookUp.get(hash(key)).isEmpty())
            dim++;

        if ((double)dim/LookUp.size() >= threshold) {
            reSize();
            reposition();
        }

        if (get(key) == null)
            LookUp.get(hash(key)).add(new Node<>(key,value));
    }

    private void reposition() {
        for (LinkedList<Node<K,V>> slot : LookUp){
            for (Node<K,V> node : slot){
                if (prehash.apply(node.key) > hash(node.key)){
                    swap(slot,node);
                }
            }
        }
    }

    private void swap(LinkedList<Node<K,V>> slot, Node<K,V> element){
        LookUp.get(LookUp.indexOf(slot)).remove(element);
        LookUp.get(hash(element.key)).add(element);
    }


    public void delete(K key)
    {
        Node<K,V> candidate = get(key);
        LookUp.get(hash(key)).remove(candidate);

        if (LookUp.get(hash(key)).isEmpty())
            dim--;

        /*if(LookUp[pos].key.equals(key)){
            LookUp[pos] = null;
        }else{
            while(!LookUp[pos].key.equals(key)){
                pos++;
            }

            LookUp[pos].state = State.BajaLogica;
        }*/
    }

    public void dump()
    {
        for (LinkedList<Node<K,V>> slot : LookUp) {
            System.out.println(slot);
        }
        System.out.println("----------------------------------------");
        /*for(int rec= 0; rec < LookUp.length; rec++)
            if (LookUp[rec] == null)
                System.out.println(String.format("slot %d is empty", rec));
            else
                System.out.println(String.format("slot %d contains %s %s", rec, LookUp[rec], LookUp[rec].state.equals(State.Ocupado)? "not deleted":"deleted"));*/
    }

    static class Node<K,V>
    {
        final K key;
        V value;

        Node(K theKey, V theValue)
        {
            key= theKey;
            value= theValue;
        }


        public String toString()
        {
            return String.format("%s=>%s", key, value );
        }
    }

}

/*
 para el lunes traigan el ejercicio de hashing de recalcular el tamaño cuando se llega al umbral resuelto
 vamos a seguir construyendo sobre esa solución
 */