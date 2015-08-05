package DynamicProgramming;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

class DepthFirstOrder {
	Queue<Integer> preOrder = new LinkedList<Integer>();
	Queue<Integer> postOrder = new LinkedList<>();
	int[] pre;
	int[] post;
	boolean hasOrder;
	boolean[] marked;
	int preCounter;
	int postCounter;
	public DepthFirstOrder(EdgeWeightedDigraph G) {
		pre = new int[G.V()];
		post = new int[G.V()];
		marked = new boolean[G.V()];
		for (int i = 0; i < G.V(); i++) {
			if (!marked[i]) {
				dfs(G, i);
			}
		}
	}	
		public void  dfs(EdgeWeightedDigraph G, int src) {
			marked[src] = true;
			pre[src] = preCounter++;
			preOrder.add(src);
			for (Edge e:G.adj(src)) {
				if (!marked[e.endNode]) {
					dfs(G, e.endNode);
				}
			}
			post[src] = postCounter++;
			postOrder.add(src);
		}
		
		Iterable<Integer>  preOrder() {
			return preOrder();
		}
		
		Iterable<Integer>  postOrder() {
			return postOrder();
		}
		
		public int pre(int vertex) {
			return pre[vertex];
		}
		public int post(int vertex) {
			return post[vertex];
		}
		
		public List<Integer> reversePostOrder() {
			Stack<Integer> reversePostorder = new Stack<Integer>();
			for(Integer vertex:postOrder) {
				reversePostorder.push(vertex);
			}
			List<Integer> list = new ArrayList<Integer>();
			while (reversePostorder.peek() != null) {
				list.add(reversePostorder.pop());
			}
			return list;
		}
		
		boolean isInTopOrder(int node1, int node2) {
			return post[node1] >= post[node2];
		}
}
