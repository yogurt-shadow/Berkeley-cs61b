import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class WeightedGraph implements Graph{
	private List<Integer>[] items;
	private int vsize;
	private int esize;
	private Set<Edge> edges;

	public WeightedGraph(int n){
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
		items[v].add(u);
	}
	} 

	public void addEdge(int u, int v, int weight){
		if(!connected(u, v)){
			esize += 1;
			items[u].add(v);
			items[v].add(u);
			Edge edge = new Edge(u, v, weight);
			edges.add(edge);
			Edge edge2 = new Edge(v, u, weight);
			edges.add(edge2);
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

	public static void main(String[] args){
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
		System.out.println(g.adj_edge(4).size());
		System.out.println(g.adj_edge(5).size());
	}
}