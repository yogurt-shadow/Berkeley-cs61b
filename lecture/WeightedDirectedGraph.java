import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class WeightedDirectedGraph implements Graph{
	private List<Integer>[] items;
	private int vsize;
	private int esize;
	private Set<Edge> edges;

	public WeightedDirectedGraph(int n){
		items = new List[n];
		vsize = n;
		esize = 0;
		for(int i = 0; i < n; i++){
			items[i] = new ArrayList<>();
		}
		edges = new HashSet<>();
	}

	@Override
	public void addEdge(int u, int v){
		if(!connected(u, v)){
			esize += 1;
		items[u].add(v);
	}
	} 

	public void addEdge(int u, int v, int weight){
		if(!connected(u, v)){
			esize += 1;
			items[u].add(v);
			Edge edge = new Edge(u, v, weight);
			edges.add(edge);
		}
	}



	@Override
	public Set<Edge> getEdges(){
		return edges;
	}

	@Override
	public int V(){
		return vsize;
	}

	@Override
	public int E(){
		return esize;
	}

	@Override
	public boolean connected(int u, int v){
		return items[u].contains(v);
	}

	@Override
	public Set<Integer> adj(int u){
		Set<Integer> u_adj = new HashSet<>();
		for(int i = vsize - 1; i >= 0; i--){
			if(connected(u, i)){
				u_adj.add(i);
			}
		}
		return u_adj;
	}

	public Set<Edge> adj_edge(int u){
		Set<Edge> uedge = new HashSet<>();
		for(Edge edge: edges){
			if(edge.getstart() == u){
				uedge.add(edge);
			}
		}
		return uedge;
	}
}