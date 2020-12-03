import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class DirectedGraph implements Graph{
	private List<Integer>[] items;
	private int vsize;
	private int esize;
	private Set<Edge> edges;

	public DirectedGraph(int n){
		items = new List[n];
		vsize = n;
		esize = 0;
		for(int i = 0; i < n; i++){
			items[i] = new ArrayList<>();
		}
		edges = new HashSet<>();
	}

	@Override
	public void addEdge(int u, int v){	// from u to v
		if(!connected(u, v)){
		esize += 1;
		items[u].add(v);
		edges.add(new Edge(u, v, 1));
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
	public boolean connected(int u, int v){  // from u to v
		return items[u].contains(v);
	}

	@Override
	public Set<Integer> adj(int u){  // from u
		Set<Integer> u_adj = new HashSet<>();
		for(int i = 0; i < vsize; i++){
			if(connected(u, i)){
				u_adj.add(i);
			}
		}
		return u_adj;
	}

	public Set<Integer> adj2(int u){
		Set<Integer> u_adj = new HashSet<>();
		for(int i = 0; i < vsize; i++){
			if(connected(i, u)){
				u_adj.add(i);
			}
		}
		return u_adj;
	}

	public static void main(String[] args){
		DirectedGraph udg = new DirectedGraph(9);
		udg.addEdge(0, 1);
		udg.addEdge(1, 2);
		udg.addEdge(1, 4);
		udg.addEdge(2, 5);
		udg.addEdge(4, 5);
		udg.addEdge(3, 4);
		udg.addEdge(5, 8);
		udg.addEdge(6, 7);
		udg.addEdge(5, 6);
		System.out.println(udg.V());
		System.out.println(udg.E());
		System.out.println(udg.adj(5).size());
		System.out.println(udg.adj2(5).size());
	}
}