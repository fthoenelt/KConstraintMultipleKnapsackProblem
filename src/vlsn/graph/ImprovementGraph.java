package vlsn.graph;

import java.io.Serial;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;

public class ImprovementGraph extends DefaultDirectedWeightedGraph<Vertex, Edge> {

  @Serial
  private static final long serialVersionUID = -8920668829716130552L;

  public ImprovementGraph(){
    super(Edge.class);
  }
}
