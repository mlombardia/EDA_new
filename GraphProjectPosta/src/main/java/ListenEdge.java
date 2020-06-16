import org.jgrapht.graph.DefaultWeightedEdge;

public class ListenEdge extends DefaultWeightedEdge {
    public String toString(){
        return getWeight() + "";
    }

    public double getWeight(){
        return super.getWeight();
    }

    public Object getSource(){
        return super.getSource();
    }

    public Object getTarget() {
        return super.getTarget();
    }
}
