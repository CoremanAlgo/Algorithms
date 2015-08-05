package DynamicProgramming;

import java.util.ArrayList;
import java.util.List;

class LongestPath {
	
	public static List<Integer> getLongestPath(EdgeWeightedDigraph G, int V, int src, int dest)  {
		
	  DepthFirstOrder dfsOrder = new DepthFirstOrder(G);
	  List<Integer> linearizedList = dfsOrder.reversePostOrder();
	  // int[] for storing the path
	  int[] edgeTo = new int[V+1];
	  // int[] for storing the Max distances from source src.
	  int[] longestDist = new int[V+1];
	  // Initialize to Negative Infinity for all elements.
	  for (int i = 0; i <= V; i++) {
	    longestDist[i] = Integer.MIN_VALUE;
	  }
	  
	  // srsc dist initialization to 0.
	  longestDist[src] = 0;
	  // index for traversing linearizedList.
	  int index = 0;
	  // Variable for storing the current node which is getting processsed in 
	  // the linearizedList.
	  int node = linearizedList.get(0);

	// we start traversing the linearized list, we ignore the elements till we 
	// reach src.
	  while(node != src) {
	    node =  linearizedList.get(index++);
	  }

	  // Loop Invariant: before the loop is executed for ith vertex between vertex   
	  // src and dest, in the linearized list,
	  // longest path from the  vertex src for all the vertices starting from src 
	  // in the linearized list, and before the vertex i in the linearized list 
	  // have been set correctly.
	  // This is true in the beginning as dist[src] has been initialized to 0.
	  // Terminates when the vertex just before the vertex dest has been 
	  // processed and the node is Pointing to the dest.
	  while (node != dest) {
	    for (Edge edge: G.adj[node]) {
	      // if the endpoint of the edge is in order with dest then adjust
	      // the dist for endPoint.
	      if (dfsOrder.isInTopOrder(edge.endNode, dest)) {
	      	if (longestDist[edge.endNode] <= longestDist[node] + edge.weight) {
	      		longestDist[edge.endNode] =	longestDist[node] + edge.weight;
	      		edgeTo[edge.endNode] = node;
	      	}
	      }
	    }
	  } // end while
	  List<Integer> path = new ArrayList<Integer>();
	  addPath(edgeTo, dest, src, path);
	  return path;
	} // end method
	
	private static void addPath(int[] edgeTo, int currentNode, int start, List<Integer> list) {
		if (currentNode == start) {
			list.add(currentNode);
			return;
		}
		addPath(edgeTo, edgeTo[currentNode], start, list);
		list.add(currentNode);
	}

}
