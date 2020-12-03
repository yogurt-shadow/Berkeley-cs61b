import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class NegativeDijkstra{
	public void shortestpath(WeightedDirectedGraph g, int start){
		List<Integer> items = new ArrayList<>();
		PostOrderDFS pod = new PostOrderDFS();
		pod.poDFS_store(g, start, items);
		Collections.reverse(items);

		int[] distTo = new int[g.V()];
		int[] edgeTo  =new int[g.V()];
		distTo[start] = 0;
		edgeTo[start] = start;
		for(int i = 0; i < g.V(); i++){
			if(i == start){
				continue;
			}
			distTo[i] = 100000;
		}
		for(Integer item: items){
			for(Edge edge: g.adj_edge(item)){
				int end = edge.getend();
				if(distTo[end] > distTo[item] + edge.getweight()){
					distTo[end] = distTo[item] + edge.getweight();
					edgeTo[end] = item;
				}
			}
		}

		for(int i = 0; i < g.V(); i++){
			System.out.println(i + " distTo:" + distTo[i] + " edgeTo:" + edgeTo[i]);
		}
	}

	public static void main(String[] args){
		WeightedDirectedGraph g = new WeightedDirectedGraph(6);
		g.addEdge(0, 3, 1);
		g.addEdge(0, 1, 1);
		g.addEdge(1, 2, 6);
		g.addEdge(3, 1, 1);
		g.addEdge(3, 4, 1);
		g.addEdge(2, 4, -20);
		g.addEdge(4, 5, 1);
		g.addEdge(2, 5, 1);
		NegativeDijkstra d = new NegativeDijkstra();
		d.shortestpath(g, 0);
	}
}