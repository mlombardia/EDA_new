import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JApplet;
import javax.swing.JFrame;

import com.opencsv.bean.CsvToBeanBuilder;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.AsSubgraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.builder.GraphTypeBuilder;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;

public class TestGUI extends JApplet
{
    private static final long serialVersionUID = 2202072534703043194L;

    private static final Dimension DEFAULT_SIZE = new Dimension(800, 600);


    /**
     * An alternative starting point for this demo, to also allow running this applet as an
     * application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args)
    {
        TestGUI applet = new TestGUI();
        applet.init();

        JFrame frame = new JFrame();
        frame.getContentPane().add(applet);
        frame.setTitle("JGraphT Adapter to JGraphX Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    @Override
    public void init() {
        // completar aca!!!!
        // 	Graph<URI, DefaultEdge> g= null;
        // 	Graph<String, DefaultWeightedEdge> g= null;
        //	Graph<String, PrintableWeightedEdge> g= null;
        Graph<Person, ListenEdge> g= null;

        try {
            g = createModelCsv();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Graph<Person,ListenEdge> gidol = generateIdol65(g);

        for (Person person : g.vertexSet()){
            System.out.println(g.incomingEdgesOf(person) + "" + person.getName());
        }

        //gidol=generateIdolReduced(gidol);

        //System.out.println(g.vertexSet().size());
        //System.out.println(g.edgeSet().size());

        //for(Person person:g.vertexSet())
        //    System.out.println(person);

        // create a visualization using JGraph, via an adapter
        JGraphXAdapter jgxAdapter = new JGraphXAdapter<>(gidol);

        setPreferredSize(DEFAULT_SIZE);
        mxGraphComponent component = new mxGraphComponent(jgxAdapter);
        component.setConnectable(false);
        component.getGraph().setAllowDanglingEdges(false);
        getContentPane().add(component);
        resize(DEFAULT_SIZE);

        // positioning via jgraphx layouts
        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);

        // center the circle
        int radius = 200;
        layout.setX0((DEFAULT_SIZE.width / 2.0) - radius);
        layout.setY0((DEFAULT_SIZE.height / 2.0) - radius);
        layout.setRadius(radius);
        layout.setMoveCircle(true);

        layout.execute(jgxAdapter.getDefaultParent());
        // that's all there is to it!...
    }

    public Graph<Person, ListenEdge> generateIdol(Graph<Person, ListenEdge> g){
        Set<ListenEdge> subedges = g.edgeSet().stream().filter(x -> x.getWeight() > 40).collect(Collectors.toSet());
        return new AsSubgraph<>(g,g.vertexSet(),subedges);
    }

    public Graph<Person, ListenEdge> generateIdolReduced(Graph<Person, ListenEdge> g){
        Set<Person> subvertexes = g.vertexSet().stream().filter(v -> g.degreeOf(v) > 0).collect(Collectors.toSet());
        return new AsSubgraph<>(g,subvertexes, g.edgeSet());
    }

    public Graph<Person, ListenEdge> generateIdol65(Graph<Person, ListenEdge> g){
        Set<Person> subvertexes = g.vertexSet().stream().filter(v -> sumOfEdges(g,v) == 103).collect(Collectors.toSet());
        return new AsSubgraph<>(g,subvertexes,g.edgeSet());
    }

    private Double sumOfEdges(Graph<Person, ListenEdge> g, Person v) {
        Double qty = 0.0;
        if (g.inDegreeOf(v) != 0) {
            for (ListenEdge listen : g.edgeSet()) {
                if (listen.getTarget().equals(v)) {
                    qty += listen.getWeight();
                }
            }
        }
        return qty;
    }

    public Graph<Person, ListenEdge> generateSpIt(Graph<Person, ListenEdge> g){
        Set<Person> subvertexes = g.vertexSet().stream().filter(v -> (v.getClass().equals(Person.class) || (v.getClass().equals(User.class) && (((User) v).getCountry().equals("Spain")||((User) v).getCountry().equals("Italy"))))).collect(Collectors.toSet());
        return new AsSubgraph<>(g,subvertexes, g.edgeSet());
    }

    private Graph<Person, ListenEdge> createModelCsv() throws FileNotFoundException {
        Graph<Person, ListenEdge> g =
                GraphTypeBuilder.<Person, ListenEdge> undirected()
                        .allowingMultipleEdges(false)
                        .allowingSelfLoops(false)
                        .edgeClass(ListenEdge.class)
                        .weighted(true)
                        .buildGraph();

        List<Person> artists = new CsvToBeanBuilder<Person>(new FileReader("C:\\Users\\maxim\\Documents\\EDA_new\\GraphProjectPosta\\src\\main\\resources\\artistssmall.tsv"))
                .withType(Person.class)
                .build()
                .parse();

        for (Person artist : artists){
            g.addVertex(artist);
        }

        List<User> users = new CsvToBeanBuilder<User>(new FileReader("C:\\Users\\maxim\\Documents\\EDA_new\\GraphProjectPosta\\src\\main\\resources\\userssmall.tsv"))
                .withType(User.class)
                .withSeparator('\t')
                .build()
                .parse();

        for (User user : users){
            g.addVertex(user);
        }

        List<Listen> listens = new CsvToBeanBuilder<Listen>(new FileReader("C:\\Users\\maxim\\Documents\\EDA_new\\GraphProjectPosta\\src\\main\\resources\\listensmall.tsv"))
                .withType(Listen.class)
                .withSeparator(',')
                .build()
                .parse();

        for (Listen listen : listens){
            System.out.println(listen);
            g.setEdgeWeight(g.addEdge(findNode(listen.getUser(),g.vertexSet()),findNode(listen.getArtist(),g.vertexSet())), listen.getQty());
        }

        return g;
    }

    private Person findNode(String string, Set<Person> vertexSet){
        for (Person person : vertexSet){
            if (string.equals(person.getName())){
                return person;
            }
        }
        return null;
    }

    private Graph<String, PrintableWieghtedEdge> createModel	() {
    // Flight route
    Graph<String, PrintableWieghtedEdge> g =
            GraphTypeBuilder.<String, PrintableWieghtedEdge> undirected()
                    .allowingMultipleEdges(false)
                    .allowingSelfLoops(false)
                    .edgeClass(PrintableWieghtedEdge.class)
                    .weighted(true)
                    .buildGraph();

    System.out.println(g.getType());

    // vertexes
        g.addVertex("HNL");
        g.addVertex("LAX");
        g.addVertex("SFO");
        g.addVertex("ORD");
        g.addVertex("DFW");
        g.addVertex("LGA");
        g.addVertex("MIA");
        g.addVertex("PVD");

    // edges
        g.setEdgeWeight( g.addEdge("HNL", "LAX"), 2555);
        Graphs.addEdge(g, "LAX", "SFO", 337);

        g.setEdgeWeight( g.addEdge("SFO", "ORD"), 1843);
        g.setEdgeWeight( g.addEdge("LAX", "ORD"), 1743);
        g.setEdgeWeight( g.addEdge("LAX", "DFW"), 1233);
        g.setEdgeWeight( g.addEdge("DFW", "ORD"), 802);
        g.setEdgeWeight( g.addEdge("ORD", "PVD"), 849);
        g.setEdgeWeight( g.addEdge("DFW", "LGA"), 1387);
        g.setEdgeWeight( g.addEdge("DFW", "MIA"), 1120);
        g.setEdgeWeight( g.addEdge("LGA", "PVD"), 142);
        g.setEdgeWeight( g.addEdge("LGA", "MIA"), 1099);
        g.setEdgeWeight( g.addEdge("MIA", "PVD"), 1205);

    return g;
}


}