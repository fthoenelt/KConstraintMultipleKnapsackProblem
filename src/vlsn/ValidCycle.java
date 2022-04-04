package vlsn;

import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import vlsn.graph.DummyVertex;
import vlsn.graph.Edge;
import vlsn.graph.Vertex;

public class ValidCycle {

  ArrayList<Vertex> label;
  ArrayList<Integer> knapsacks;
  int c;
  int r;
  boolean isCycle;

  public ValidCycle(int r, Edge... edges){

    this.r = r;
    this.label = new ArrayList<>(r);
    this.knapsacks = new ArrayList<>(r);

    for(Edge e : edges){
      this.c += e.getEdgeWeight();
      if(this.label.isEmpty()){
        label.add(e.getSourceVertex());
        knapsacks.add(e.getSourceVertex().getKnapsackID());
      }
      label.add(e.getTargetVertex());
      assert(!knapsacks.contains(e.getTargetVertex().getKnapsackID()));
      knapsacks.add(e.getTargetVertex().getKnapsackID());
    }
    isCycle = false;


  }

  ValidCycle(ValidCycle clone){
    knapsacks = (ArrayList<Integer>) clone.knapsacks.clone();
    label = (ArrayList<Vertex>) clone.label.clone();
    c = clone.c;
    r = clone.r;
    isCycle = clone.isCycle;

  }

  public ArrayList<Vertex> getLabel() {
    return label;
  }

  public ArrayList<Integer> getKnapsacks() {
    return knapsacks;
  }


  public boolean isCycle(){
    return this.isCycle;
  }
  public Vertex getHead(){
    assert label.size()!= 0;
    return label.get(label.size()-1);
  }

  public Vertex getTail(){
    assert label.size()!=0;
    return label.get(0);
  }

  public boolean containsLabel(int labelID){
    return(knapsacks.contains(labelID));
  }

  public ValidCycle addVertex(Edge e){

    if(e.getSourceVertex().equals(label.get(label.size()-1))){
      if(e.getTargetVertex().equals(label.get(0))){
        this.c += e.getEdgeWeight();
        isCycle = true;
        return this;
      }else{
        isCycle = false;
      }
      this.c += e.getEdgeWeight();
      label.add(e.getTargetVertex());
      assert(!knapsacks.contains(e.getTargetVertex().getKnapsackID()));
      knapsacks.add(e.getTargetVertex().getKnapsackID());
      return this;
    }else{
      System.out.println("------------------");
      for(int i = 0; i <this.label.size(); i++){
        System.out.println(i+": "+label.get(i));
      }
      System.out.println(e.getSourceVertex().getVertexID() + "->" + e.getTargetVertex().getVertexID());
      assert false;
    }
    return null;
  }

  public int getC(){
    return this.c;
  }

  public int getK(){
    return label.size()-1;
  }

  //True iff this dominates c
  public boolean dominates(@NotNull ValidCycle vc){
    boolean d = true;
    d = this.getHead()==vc.getHead() && this.getTail()==vc.getTail() && this.getC() < vc.getC() &&this.getK() == vc.getK();
    for(Vertex v: this.label){
      if(!vc.getLabel().contains(v)) d = false;
    }
    return d;
  }

  @Override
  public String toString(){
    StringBuilder str = new StringBuilder("ValidCycle(");
    for(Vertex v: label){
      str.append("(").append(v.getVertexID()).append(", dummy:").append(v instanceof DummyVertex).append("), ");
    }
    str.append(")");
    return str.toString();
  }


}
