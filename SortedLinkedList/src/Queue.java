import java.util.Iterator;

public class Queue<T extends Comparable<? super T>> implements QueueService<T>{
    protected Node firstElement;
    protected Node lastElement;
    protected int size;
    protected int limit_iter;

    @Override
    public void add(T elem) {
        Node newNode = new Node(elem, firstElement);
        if (isEmpty()){
            firstElement = newNode;
        }else{
            Node aux = lastElement;
            aux.next = newNode;
        }
        lastElement = newNode;
        size++;
    }

    @Override
    public T remove() {
        Node aux = firstElement;
        firstElement = firstElement.next;
        size--;
        return aux.value;
    }

    @Override
    public T peek() {
        return firstElement.value;
    }

    @Override
    public boolean isEmpty() {
        return firstElement == null;
    }

    @Override
    public void dump() {
        for (Node i=firstElement; i != lastElement; i = i.next){
            System.out.println(i.value);
        }
        System.out.println(lastElement.value);
    }

    @Override
    public int size() {
        return size;
    }

    public int getLimit(){
        return limit_iter;
    }

    public void setLimit(int limit){
        limit_iter = limit;
    }

    public Iterator<T> iterator() {
        switch (limit_iter){
            case 0:
                return new QueueIterator();
            default:
                return new QueueIterator(limit_iter);
        }
    }

    private class QueueIterator implements Iterator<T>{

        private Node current;
        private int iter;
        private int limit;

        private QueueIterator(){
            current = firstElement;
            this.limit=1;
        }

        private QueueIterator(int limit){
            current = firstElement;
            this.limit=limit;
        }

        @Override
        public boolean hasNext() {
            return iter < limit;
        }

        @Override
        public T next() {
            if (current == lastElement)
                iter++;
            Node aux = current;
            current = current.next;
            return aux.value;
        }
    }

    protected final class Node {

        protected T value;
        protected Node next;

        // do not accept nulls in the data
        Node(T theValue, Node theNext) {
            if (theValue == null)
                throw new RuntimeException("Null is not accepted for data");
            value = theValue;
            next = theNext;
        }
    }


}
