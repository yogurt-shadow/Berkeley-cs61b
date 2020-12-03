import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Set;
import java.util.HashSet;


public class Dijkstra{

	public void shortestpath(WeightedGraph g, int start){
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
		WeightedGraph g = new WeightedGraph(7);
		g.addEdge(0, 1, 2);
		g.addEdge(0, 2, 1);
		g.addEdge(1, 2, 5);
		g.addEdge(1, 4, 3);
		g.addEdge(4, 2, 1);
		g.addEdge(2, 5, 15);
		g.addEdge(6, 5, 1);
		g.addEdge(4, 6, 5);
		g.addEdge(4, 5, 4);
		g.addEdge(6, 3, 1);
		g.addEdge(1, 3, 11);
		g.addEdge(3, 4, 2);
		d.shortestpath(g, 0);
	}
}