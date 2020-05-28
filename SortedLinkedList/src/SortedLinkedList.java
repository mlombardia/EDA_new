import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedLinkedList<T extends Comparable<? super T>>
implements SortedListService<T>{

	protected Node firstElement;
	protected Node lastElement;
	protected int size;

	@Override
	public void dump() {
		for (Node rec= firstElement; rec != null; rec= rec.next) {
			System.out.println(rec.value);
		}
	
	}

	@Override
	public boolean isEmpty() {
		return firstElement == null;
	}

	@Override
	public void add(T element) {
		Node prev = firstElement;
		Node rec = firstElement;

		while(rec != null && rec.value.compareTo(element) < 0) {
			// go on
			prev = rec;
			rec = rec.next;
		}

		// repeated?
		if(rec != null && rec.value.compareTo(element) == 0) {
			System.err.println(String.format("Insertion failed. %s repeated", element));
			return;
		}


		Node newNode = new Node(element,rec);
		// does the first element change?
		if (prev == rec) {
			firstElement = newNode;
		} else {
			prev.next = newNode;
		}

		//change last element
		if(rec == null)
			lastElement = newNode;
		size++;
	}

	@Override
	public void delete(T element) {
		if (isEmpty()) {
			System.err.println("List is empty");
			return;
		}
		Node prev = firstElement;
		Node rec = firstElement;
		while (rec != null && rec.value.compareTo(element) < 0){
			prev=rec;
			rec=rec.next;
		}

		/*if(rec == null || rec.value.compareTo(element) != 0){
			System.err.println(String.format("Element %s not found", element));
			return;
		}

		if(prev == rec)
			firstElement = prev.next;
		else
			prev.next = rec.next;

		if(rec == null)
			lastElement = prev;*/

		if (rec == null){
			System.err.println(String.format("Element %s not found", element));
			return;
		}else {
			Node aux = rec.next;
			if (aux != null) {
				if (prev == rec) {
					firstElement = aux;
				} else {
					prev.next = rec.next;
				}
			} else {
				if (prev == rec) {
					firstElement = null;
				} else {
					prev.next = null;
					lastElement = prev;
				}
			}
		}
		size--;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public T getMin() {
		if (isEmpty()) {
			System.err.println("List is empty");
			return null;
		}
		return firstElement.value;
	}

	@Override
	public T getMax() {
		if (isEmpty()) {
			System.err.println("List is empty");
			return null;
		}
		return lastElement.value;
	}

	@Override
	public Iterator<T> iterator() {
		return new SortedLinkedListIterator();
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


	protected class SortedLinkedListIterator implements Iterator<T> {

		protected Node current;

		protected SortedLinkedListIterator(){
			current = firstElement;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Node aux = current;
			current = current.next;
			return aux.value;
		}
	}
}
