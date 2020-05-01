package hash;

import java.util.Map; 
import java.util.function.Function;

import hash.Hash2.Node;

public class Hash<K, V> 
{
	private int initialLookupSize= 10;
	
	// estática. No crece. Espacio suficiente...
	private Node<K,V>[] LookUp= new Node[initialLookupSize];
	
	private Function<? super K, Integer> prehash;
	
	public Hash( Function<? super K, Integer> mappingFn)
	{
		prehash= mappingFn;
	}
	
	// ajuste al tamaño de la tabla
	 private int hash(K key) 
	 {
		 if (key == null)
			 throw new RuntimeException("No key provided");
		 
		 return prehash.apply(key) % LookUp.length;
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
		return LookUp[  hash( key) ];
	}
	
	// insert = update
	public void insert(K key, V value)
	{
		LookUp[  hash( key) ] = new Node<K,V>(key,  value);
	}
	

	public void delete(K key)
	{
		LookUp[  hash( key) ] = null;
	}

	public void dump()
	{
		for(int rec= 0; rec < LookUp.length; rec++)
			if (LookUp[rec] == null)
				System.out.println(String.format("slot %d is empty", rec));
			else
				System.out.println(String.format("slot %d contains %s", rec, LookUp[rec]));
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
			return String.format("key=%s, value=%s", key, value );
		}
	}

}
