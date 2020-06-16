import java.util.LinkedList;
import java.util.function.Function;

public class HashBagImpl<T>{

    private double threshold;
    private int dim;
    private int multiplier;
    private LinkedList<LinkedList<Node<T>>> LookUp= new LinkedList<>();

    private Function<? super T, Integer> prehash;

    public HashBagImpl()
    {
        prehash= new Function() {
            @Override
            public Integer apply(Object o) {
                return (int)o;
            }
        };
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
    private int hash(T key)
    {
        if (key == null)
            throw new RuntimeException("No key provided");

        return prehash.apply(key) % LookUp.size();
    }



    public void add(T value){

        if (LookUp.get(hash(value)).isEmpty())
            dim++;

        if ((double)dim/LookUp.size() >= threshold) {
            reSize();
            reposition();
        }

        Node<T> candidate = get(value);
        if (candidate == null)
            LookUp.get(hash(value)).add(new Node<>(value));
        else
            candidate.count++;
    }

    private void reposition() {
        for (LinkedList<Node<T>> slot : LookUp){
            for (Node<T> node : slot){
                if (prehash.apply(node.key) > hash(node.key)){
                    swap(slot,node);
                }
            }
        }
    }

    private void swap(LinkedList<Node<T>> slot, Node<T> element){
        LookUp.get(LookUp.indexOf(slot)).remove(element);
        LookUp.get(hash(element.key)).add(element);
    }

    public void remove(T value){
        Node<T> candidate = get(value);
        LookUp.get(hash(value)).remove(candidate);

        if (LookUp.get(hash(value)).isEmpty())
            dim--;
    }

    public int getCount(T value){
        Node<T> candidate = get(value);
        if (candidate != null)
            return candidate.count;
        return 0;
    }

    private Node<T> get(T key)
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

    static class Node<K>
    {
        final K key;
        int count;

        Node(K theKey)
        {
            count = 1;
            key= theKey;
        }


        public String toString()
        {
            return String.format("%s=>%d", key, count);
        }
    }
}
