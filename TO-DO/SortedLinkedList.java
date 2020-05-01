package eda;

import java.util.Iterator;

public class SortedLinkedList<T extends Comparable<? super T>>
implements SortedListService<T>{

	private Node firstElement;

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

		// does the first element change?
		if (prev == rec) {
			firstElement = new Node(element, rec);
		} else {
			prev.next = new Node(element, rec);
		}

	}

	@Override
	public void delete(T element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public T getMin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getMax() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	private final class Node {

		private T value;
		private Node next;

		// do not accept nulls in the data
		Node(T theValue, Node theNext) {
			if (theValue == null)
				throw new RuntimeException("Null is not accepted for data");
			value = theValue;
			next = theNext;
		}

	}


}
