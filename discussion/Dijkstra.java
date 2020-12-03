import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Set;
import java.util.HashSet;


public class Dijkstra{

	public void shortestpath(WeightedDirectedGraph g, int start){
		ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
		int[] distTo = new int[g.V()];
		int[] edgeTo = new int[g.V()];
		for(int i = 0; i < g.V(); i++){
			if(i == start){
				distTo[i] = 0;
				edgeTo[i] = i;
			}
			else{
				distTo[i] = 1000000;
			}
		}

		for(int i = 0; i < g.V(); i++){
			pq.add(i, distTo[i]);
		}

		while(pq.size() > 0){
			int current = pq.removeSmallest();
			for(Edge edge: g.adj_edge(current)){

				int end = edge.getend();
				if(!pq.contains(end)){
					continue;
				}
				if(distTo[end] > distTo[current] + edge.getweight()){
					distTo[end] = distTo[current] + edge.getweight();
					edgeTo[end] = current;
					pq.changePriority(end, distTo[end]);
				}
			}
		}

		for(int i = 0; i < g.V(); i++){
			System.out.println(i + " distTo:" + distTo[i] + " edgeTo:" + edgeTo[i]);
		}
		}


	public static void main(String[] args){
		Dijkstra d =new Dijkstra();
		WeightedDirectedGraph g = new WeightedDirectedGraph(7);
		g.addEdge(0, 1, 1);
		g.addEdge(0, 3, 2);
		g.addEdge(1, 2, 3);
		g.addEdge(2, 5, 2);
		g.addEdge(2, 6, 4);
		g.addEdge(5, 6, 1);
		g.addEdge(3, 4, 3);
		g.addEdge(4, 6, 3);
		d.shortestpath(g, 0);
	}
}