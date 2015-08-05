package DynamicProgramming;

import java.util.*;
class Edge implements Cloneable{
	int source;
	int endNode;
	int weight;
	Edge(int source, int dest, int weight) {
		this.source = source;
		this.endNode = dest;
		this.weight = weight;
	}
	
	public Object clone() {
		return new Edge(this.source, this.endNode, this.weight);
	}
}

public class EdgeWeightedDigraph {
	int V;
	int E;
	List<Edge>[] adj;
	
	@SuppressWarnings("unchecked")
	public EdgeWeightedDigraph(int V ) {
		this.V = V;
		this.adj = (ArrayList<Edge>[])  new Object[V];
		for ( int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<Edge>();
		}
		
	}
	
	public EdgeWeightedDigraph clone() {
		EdgeWeightedDigraph retVal = new EdgeWeightedDigraph(V);
		for (int i = 0; i < V ; i++) {
			for (Edge edge:adj(i)) {
				retVal.addEdge((Edge)edge.clone());
			}
		}
		return retVal;
	}
	
	public EdgeWeightedDigraph reverse() {
		EdgeWeightedDigraph retVal = new EdgeWeightedDigraph(V);
		for (int i = 0; i < V ; i++) {
			for (Edge edge:adj(i)) {
				retVal.addEdge(new Edge(edge.endNode, edge.source, edge.weight));
			}
		}
		return retVal;
	}
	
	public void addEdge(Edge e) {
		adj[e.source].add(e);
		E++;
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	public List<Edge> adj(int source) {
		return adj[source];
	}
}

