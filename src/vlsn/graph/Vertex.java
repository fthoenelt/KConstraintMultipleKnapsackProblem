package vlsn.graph;

import knapsack.Item;

public class Vertex {

  final int vertexID;
  int knapsackID;

  public Vertex(int vertexID, int knapsackID){
    this.vertexID = vertexID;
    this.knapsackID = knapsackID;
  }

  public int getKnapsackID(){
    return this.knapsackID;
  }

  public int getVertexID() {
    return vertexID;
  }
}
