package vlsn.graph;


import java.io.Serial;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Edge extends DefaultWeightedEdge {
  @Serial
  private static final long serialVersionUID = -5583266973805531618L;

  public Vertex[] getVertices() {
    return (new Vertex[]{(Vertex) this.getTarget(), (Vertex) this.getSource()});
  }

  public Vertex getSourceVertex() {
    return (Vertex) this.getSource();
  }

  public Vertex getTargetVertex() {
    return (Vertex) this.getTarget();
  }

  public double getEdgeWeight(){
    return this.getWeight();
  }
}
