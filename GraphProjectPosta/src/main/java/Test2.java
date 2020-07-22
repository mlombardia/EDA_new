public class Test2 {
    static GraphService<Character> createModel()
    {
        GraphService<Character> g =
                GraphFactory.create(GraphFactory.GraphType.DENSE);

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

        g.removeEdge('C', 'E');
        g.removeEdge('F', 'X');
        g.removeEdge('E', 'B');

        return g;
    }
    public static void main(String[] args) {

        GraphService<Character> g = createModel();

        g.dump();
        g.printDFS('A');
        System.out.println("#cc:" + g.connectedComponents());


        g.removeEdge('A', 'B');
        g.removeEdge('F', 'E');
        g.dump();
        System.out.println("#cc: " + g.connectedComponents());

        g.removeEdge('X', 'B');
        g.dump();
        System.out.println("#cc: " + g.connectedComponents());


        g.removeVertex('X');
        g.dump();
        System.out.println("#cc: " + g.connectedComponents());


        g = GraphFactory.create(GraphFactory.GraphType.DENSE);
        g.dump();
        System.out.println("#cc:  " + g.connectedComponents());

    }

    }
