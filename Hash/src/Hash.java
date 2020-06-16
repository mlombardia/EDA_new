import java.util.function.Function;

public class Hash<K, V> 
{
	enum State {BajaLogica, Ocupado}
	private int initialLookupSize= 10;
	private double threshold;
	private int dim;
	
	// estatica. No crece. Espacio suficiente...
	private Node<K,V>[] LookUp= new Node[initialLookupSize];
	
	private Function<? super K, Integer> prehash;
	
	public Hash( Function<? super K, Integer> mappingFn)
	{
		prehash= mappingFn;
		dim = 0;
		threshold = 0.75;
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
		int i = hash(key);
		if(LookUp[i] != null) {
			if (LookUp[i].key.equals(key)) {
				return LookUp[i];
			} else {
				while (LookUp[i] != null && !LookUp[i].key.equals(key)) {
					i++;
				}
				if (LookUp[i] != null && LookUp[i].key.equals(key))
					return LookUp[i];
			}
		}
		return null;
	}
	
	// insert = update
	public void insert(K key, V value)
	{
		if((double)dim/LookUp.length >= threshold){
			Node<K,V>[] aux = new Node[LookUp.length + initialLookupSize];
			System.arraycopy(LookUp,0,aux,0, LookUp.length);
			LookUp = aux;
			reposition(LookUp);
		}
		if(get(key) == null) {
			int pos = hash(key);
			if (LookUp[pos] != null || (LookUp[pos] != null && LookUp[pos].state.equals(State.Ocupado))) {
				while (LookUp[pos] != null && (LookUp[pos] != null && LookUp[pos].state.equals(State.Ocupado))) {
					pos++;
				}
			}
			LookUp[pos] = new Node<>(key, value);
			dim++;
		}
	}

	private void reposition(Node<K,V>[] lookUp) {
		for(int i = 0; i < lookUp.length; i++){
			if(lookUp[i] != null){
				if(i != hash(lookUp[i].key)){
					LookUp[i] = swap(lookUp[i]);
				}
			}
		}
	}

	private Node<K,V> swap(Node<K,V> element){
		insert(element.key,element.value);
		dim--;
		return null;
	}


	public void delete(K key)
	{
		int pos = hash(key);
		if(LookUp[pos].key.equals(key)){
			LookUp[pos] = null;
		}else{
			while(!LookUp[pos].key.equals(key)){
				pos++;
			}
			
			LookUp[pos].state = State.BajaLogica;
		}
		dim--;
	}

	public void dump()
	{
		for(int rec= 0; rec < LookUp.length; rec++)
			if (LookUp[rec] == null)
				System.out.println(String.format("slot %d is empty", rec));
			else
				System.out.println(String.format("slot %d contains %s %s", rec, LookUp[rec], LookUp[rec].state.equals(State.Ocupado)? "not deleted":"deleted"));
	}

	public int size(){
		return dim;
	}
	
	static class Node<K,V> 
	{
		final K key;
		V value;
		State state;

		Node(K theKey, V theValue)
		{
			key= theKey;
			value= theValue;
			state = State.Ocupado;
		}
		
		
		public String toString()
		{
			return String.format("key=%s, value=%s", key, value );
		}
	}

}

/*
 para el lunes traigan el ejercicio de hashing de recalcular el tamaño cuando se llega al umbral resuelto
 vamos a seguir construyendo sobre esa solución
 */