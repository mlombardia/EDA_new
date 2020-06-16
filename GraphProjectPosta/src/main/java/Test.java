import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.builder.GraphTypeBuilder;

import java.util.List;

public class Test {
	
	
	static GraphService<Character> createModel1()
	{
		GraphService<Character> g = GraphFactory.create(GraphFactory.GraphType.DENSE);
		g.addEdge('C', 'G');
		g.addEdge('E', 'B');
		g.addEdge('A', 'F');
		g.addEdge('F', 'E');
		g.addEdge('B', 'F');
		g.addEdge('A', 'B');
		g.addVertex('T');
		g.addVertex('U');
		g.addEdge('T', 'U');
		g.addEdge('G', 'F');
		g.addEdge('G', 'U');
		
		return g;
	}
	
	public static void main2(String[] args) {
		GraphService<Character> g = createModel1();
		
		System.out.println(String.format("#vertices: %d", g.vertexesSize() ));
		System.out.println(String.format("#edges: %d", g.edgesSize() ));
	
		// degree de cada nodo
		for(Character aV: g.getVertices()) {
			System.out.println(
		 				String.format("vertex %s has degree %d", aV, g.degree(aV)));
		}
		 	
		g.dump();
	 	
	 	g.printBFS('E');
	 	
	 	System.out.println("Recorrido BFS desde E con iterador");
	 		 	for (Character aVertex : g.getBFS('E')) {
			System.out.println(aVertex);
		}
	 		 	

	}
	
	
	
	static GraphService<Character> createModel2()
	{
	 	GraphService<Character> g = GraphFactory.create(GraphFactory.GraphType.DENSE);
	 	g.addEdge('B', 'A');
	 	g.addEdge('A', 'C');
	 	g.addEdge('B', 'E');
	 	g.addEdge('C', 'E');
	 	g.addEdge('H', 'E');
	 	g.addEdge('E', 'F');
	 	g.addEdge('F', 'H');
	 	g.addEdge('H', 'G');
	 	g.addEdge('C', 'H');
	 	g.addEdge('D', 'T');

	 	g.addVertex('X');
	 	
	 	g.addEdge('B', 'X');
		g.addEdge('F', 'X');
		
		g.addEdge('X', 'X');
		g.addEdge('X', 'X');

	 	g.addVertex('M');		// isolated
		g.addVertex('F');	

		
		return g;
	}
	
	public static void main(String[] args) {
		Graph<String, DefaultWeightedEdge> flight = GraphTypeBuilder.<String, DefaultWeightedEdge>
				undirected().allowingMultipleEdges(false)
				.allowingSelfLoops(false).weighted(true)
				.edgeClass(DefaultWeightedEdge.class)
				.buildGraph();
		flight.addVertex("HNL");
		flight.addVertex("LAX");
		flight.addVertex("SFO");
		flight.addVertex("ORD");
		flight.addVertex("DFW");
		flight.addVertex("LGA");
		flight.addVertex("MIA");
		flight.addVertex("PVD");

		flight.setEdgeWeight( flight.addEdge("HNL", "LAX"), 2555);
		Graphs.addEdge(flight, "LAX", "SFO", 337);

		flight.setEdgeWeight( flight.addEdge("SFO", "ORD"), 1843);
		flight.setEdgeWeight( flight.addEdge("LAX", "ORD"), 1743);
		flight.setEdgeWeight( flight.addEdge("LAX", "DFW"), 1233);
		flight.setEdgeWeight( flight.addEdge("DFW", "ORD"), 802);
		flight.setEdgeWeight( flight.addEdge("ORD", "PVD"), 849);
		flight.setEdgeWeight( flight.addEdge("DFW", "LGA"), 1387);
		flight.setEdgeWeight( flight.addEdge("DFW", "MIA"), 1120);
		flight.setEdgeWeight( flight.addEdge("LGA", "PVD"), 142);
		flight.setEdgeWeight( flight.addEdge("LGA", "MIA"), 1099);
		flight.setEdgeWeight( flight.addEdge("MIA", "PVD"), 1205);

		System.out.println(flight);

		printShortestPath(flight, "PVD", "PVD");

		GraphService<Character> g = createModel2();
		
	 	System.out.println(String.format("#vertices: %d", g.vertexesSize() ));
	 	System.out.println(String.format("#edges: %d", g.edgesSize() ));

	 	for(Character aV: g.getVertices()) {
	 		System.out.println(
	 				String.format("vertex %s has degree %d", aV, g.degree(aV)));
	 	}
	 	
	 	g.dump();
	 	
	 	g.removeEdge('E', 'H');
	 	g.removeVertex('X');
		g.removeVertex('M');
		g.removeVertex('E');
	 	
	 	g.removeEdge('X',  'X');
	 	
	 	System.out.println(String.format("#vertices: %d", g.vertexesSize() ));
	 	System.out.println(String.format("#edges: %d", g.edgesSize() ));

	 	for(Character aV: g.getVertices()) {
	 		System.out.println(
	 				String.format("vertex %s has degree %d", aV, g.degree(aV)));
	 	}
	 	g.dump();
	 	
	 	
	 	System.out.println("Recorrido BFS desde W");
	 	g.printBFS('W');
	 	
	 	
	 	System.out.println("Recorrido BFS desde T");
	 	g.printBFS('T');
	 	
	 	
	 	System.out.println("Recorrido BFS desde C");
	 	g.printBFS('C');
	 	
	 	System.out.println("Recorrido BFS desde C con iterador");
	 	for (Character aVertex : g.getBFS('C')) {
			System.out.println(aVertex);
		}
	}

	public static void printShortestPath(Graph<String, DefaultWeightedEdge> graph, String source, String sink){
		GraphPath<String, DefaultWeightedEdge> path;
		path = DijkstraShortestPath.findPathBetween(graph, source, sink);
		System.out.println("Shortest path found: "+ (path.getEdgeList().size() == 0? "[]" : path) + "\n"+ path.getWeight());
	}
}
