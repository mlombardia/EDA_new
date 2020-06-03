import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class AdjacencyMatrixGraph<V> implements GraphService<V> {

		// V[];
		final private ArrayList<V> vertexes;
		
		// boolean[][];  
		final private Matrix2D<Boolean> matrix;


	    public AdjacencyMatrixGraph() {
		   vertexes= new ArrayList<V>();
		   matrix= new Matrix2D<Boolean>();
		    
		  }
	    
		@Override
		public boolean isEmpty() {
			return vertexes.isEmpty();
		}
	    
		@Override
		public boolean addVertex(V element) {
			// no repetidos (segun equals)
			if (vertexes.contains(element))
				return false;
			
			// append
			vertexes.add(element);
			
			// append edges
			matrix.append(false);
			return true;
		}
		

		
		@Override
		public boolean addEdge(V element1, V element2) {
			// no repetidos!
			addVertex(element1);
			addVertex(element2);
			
			int pos1= vertexes.indexOf(element1);
			int pos2= vertexes.indexOf(element2);
			
			if (matrix.get(pos1, pos2) && matrix.get(pos2,  pos1) )
				return false;
			
			matrix.set(pos1, pos2, true);
			matrix.set(pos2, pos1, true);
			return true;
		}
	
		@Override
		public int vertexesSize() {
			return vertexes.size();
		}
		  
		@Override
		public  int edgesSize() {
			  int rta= 0;
			  
			  for(int row= 0; row< matrix.cantElements() ; row++) {
					 for(int col= row; col< matrix.cantElements() ; col++) {
						if ( matrix.get(row,  col) )
							rta++;
					 }
			}
			 return rta;
		  }
		  
	    private void listVertexes()  {
			for (V node : vertexes) {
				System.out.println(node);
			}
		 }
		 
	    private void listEdges()
		 {
			 for(int row= 0; row< matrix.cantElements() ; row++) {
				 for(int col= row; col< matrix.cantElements() ; col++) {
					if ( matrix.get(row,  col) )
						System.out.print(String.format("(%s - %s) ",
								vertexes.get(row), vertexes.get(col)) );
				 }
			 }
			 System.out.println();
		 }
		 
		public void dump()
		{
			listVertexes();
			listEdges();
		}
		
		
		@Override
		public int degree(V vertex) {
			int pos= vertexes.indexOf(vertex);
			
			int degree= 0;
			for(int row= 0; row < matrix.cantElements(); row++)
				if (matrix.get(row, pos))
					degree++;
			
			return degree;
		}

		@Override
		public boolean removeVertex(V aVertex) {
			int index= vertexes.indexOf(aVertex);

			if ( vertexes.remove(aVertex) ) {
				matrix.remove(index);
				return true;
			}
			return false;
		}

		@Override
		public boolean removeEdge(V aVertex, V otherVertex) {
			int pos1= vertexes.indexOf(aVertex);
			int pos2= vertexes.indexOf(otherVertex);

			if(pos1 == -1 || pos2 == -1)
				return false;
			
			if (matrix.get(pos1,  pos2) && matrix.get(pos2,  pos1) ) {
				matrix.set(pos1, pos2, false);
				matrix.set(pos2, pos1, false);
				return true;
			}
			
			return false;
		}

		@Override
		public Iterable<V> getVertices() {
			return vertexes;

		}
		


	    @Override
	    public void printBFS(V startNode) {
			Queue<Integer> queue= new LinkedList<>();
			ArrayList<Boolean> visited= new ArrayList<>();;

			int currentPos= vertexes.indexOf(startNode);
			if (currentPos == -1)
				return;

			vertexes.stream().forEach(x-> visited.add(false));
			
			queue.add(currentPos);
			
			while(! queue.isEmpty()) {
				currentPos= queue.remove();
				if (visited.get(currentPos))
					continue;
				System.out.println(String.format("%s", vertexes.get(currentPos)));
				visited.set(currentPos, true);

				// proceso lista de adyacencia usando la matriz de adyacencia si 
				// no fue visitado el vecino aun
				for(int row= 0; row < matrix.cantElements(); row++) {
					if (matrix.get(row, currentPos) && ! visited.get(row))
						queue.add(row);
				}
			}
	    }
	    
		@Override
		public Iterable<V> getBFS(V startNode) {
			return new Iterable<V>() {
				
				@Override
				public Iterator<V> iterator() {
					return new myIteratorBFS(startNode);
				}
				};
		}
		
		class myIteratorBFS implements Iterator<V>
		{
			private Queue<Integer> queue= new LinkedList<>();
			private ArrayList<Boolean> visited= new ArrayList<>();

			myIteratorBFS(V startNode) {
				int currentPos= vertexes.indexOf(startNode);
				if (currentPos == -1)
					return;

				vertexes.stream().forEach(x-> visited.add(false));
			
				queue.add(currentPos);
			}
			
			@Override
			public boolean hasNext() {
				while (! queue.isEmpty() )
				{
					Integer posAuxi = queue.peek();
					if (!visited.get(posAuxi)) 
						return true;

					queue.remove();
				}
				return false;			
				}

			@Override
			public V next() {
				Integer currentPos= queue.remove();
				visited.set(currentPos, true);
				
				// proceso lista de adyacencia usando la matriz de adyacencia si 
				// no fue visitado el vecino aun
				for(int row= 0; row < matrix.cantElements(); row++) {
						if (matrix.get(row, currentPos) && ! visited.get(row))
							queue.add(row);
				}
				return vertexes.get(currentPos);
			}
		}


	    // clasico que usa recursion
		// FALTA
	    public void printDFSClassic(V startNode) {

	    	int currentPos= vertexes.indexOf(startNode);
			if (currentPos == -1)
				return;

			ArrayList<Boolean> visited= new ArrayList<>();;
			vertexes.stream().forEach(x-> visited.add(false));

			printDFSClassic(currentPos, visited);

	    }
	    
	    
	    // clasico usa recursion
	    // FALTA
	    public void printDFSClassic(Integer currentPos, ArrayList<Boolean> visited) {
			

	    }

	    // clasico pero sin recursion!
	    // FALTA
	    @Override
	    public void printDFS(V startNode) {

	    }

	    
		@Override
		public Iterable<V> getDFS(V startNode) {
			return new Iterable<V>() {
				
				@Override
				public Iterator<V> iterator() {
					return new myIteratorDFS(startNode);
				}
				};
					
		}
		
		// FALTA !!!!!
		class myIteratorDFS implements Iterator<V>
		{

			public myIteratorDFS(V startNode) {
			}

			@Override
			public boolean hasNext() {
				return false;
			}

			@Override
			public V next() {
				return null;
			}
			
		}




}