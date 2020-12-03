import java.util.Map;
import java.util.HashMap;

public class MST{
	public void prim(WeightedGraph g){
		/**
		* use prim's algorithm
		*/
		ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
		int[] distTo = new int[g.V()];
		int[] edgeTo = new int[g.V()];
		Map<Integer, Edge> map = new HashMap<>();
		distTo[0] = 0;
		edgeTo[0] = 0;
		map.put(0, new Edge(0, 0, 1));
		for(int i = 1; i < g.V(); i++){
			distTo[i] = 1000000;
		}
		for(int i = 0; i < g.V(); i++){
			pq.add(i,distTo[i]);
		}
		while(pq.size() > 0){
			int current = pq.removeSmallest();
			edgeTo[current] = map.get(current).getstart();
			for(Edge edge: g.adj_edge(current)){
				int end = edge.getend();
				if(!pq.contains(end)){
					continue;
				}
				if(distTo[end] > edge.getweight()){
					distTo[end] = edge.getweight();
					pq.changePriority(end, distTo[end]);
					map.put(end, edge);
				}
			}
		}
		for(int i = 0; i < g.V(); i++){
			System.out.println(i + " edgeTo: " + edgeTo[i]);
		}
	}

	public void kruskal(WeightedGraph g){
		/**
		 * use kruskal's algorithm
		 */
		ArrayHeapMinPQ<Edge> pq = new ArrayHeapMinPQ<>();
		for(Edge edge: g.getEdges()){
			pq.add(edge, edge.getweight());
		}
		UnionFind uf = new UnionFind(g.V());

		WeightedGraph g2 = new WeightedGraph(g.V());
		while(g2.E() < g2.V() - 1){
			Edge edge = pq.removeSmallest();
			if(uf.connected(edge.getstart(), edge.getend())){
				continue;
			}
			g2.addEdge(edge.getstart(), edge.getend(), edge.getweight());
			uf.union(edge.getstart(), edge.getend());
		}

		for(Edge edge: g2.getEdges()){
			System.out.println(edge.getstart() + " -- " + edge.getend());
		}
	}

	public static void main(String[] args){
		WeightedGraph g = new WeightedGraph(6);
		g.addEdge(0, 1, 2);
		g.addEdge(0, 2, 1);
		g.addEdge(1, 2, 2);
		g.addEdge(1, 3, 1);
		g.addEdge(2, 3, 2);
		g.addEdge(3, 4, 3);
		g.addEdge(2, 4, 3);
		g.addEdge(3, 5, 4);
		g.addEdge(4, 5, 1);
		MST mst = new MST();
		mst.prim(g);
	}
}