import java.util.Iterator;
import java.util.LinkedList;

public class SortedLinkedListAllowsRemoves<T extends Comparable<? super T>> extends SortedLinkedList<T>{


    @Override
    public Iterator<T> iterator(){
        return new SortedLinkedListIteratorAllowsRemoves();
    }

    private class SortedLinkedListIteratorAllowsRemoves extends SortedLinkedListIterator{

        private Node previous;

        private SortedLinkedListIteratorAllowsRemoves(){
            super();
            previous = firstElement;
        }

        @Override
        public T next(){

            if(current != firstElement){
                if(previous.next != current){
                    previous = previous.next;
                }
            }
            return super.next();
            /*if(previous != current)
                previous = previous.next;
            return super.next();*/
        }

        @Override
        public void remove(){
            if(previous == firstElement)
                firstElement=current;

            previous.next = current;

            if(current == null)
                lastElement = previous;

            size--;
        }
    }
}
