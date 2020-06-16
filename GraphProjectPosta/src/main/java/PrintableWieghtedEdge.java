import org.jgrapht.graph.DefaultWeightedEdge;

public class PrintableWieghtedEdge extends DefaultWeightedEdge {
    @Override
    public String toString() {
        return String.valueOf(this.getWeight());
    }
}
