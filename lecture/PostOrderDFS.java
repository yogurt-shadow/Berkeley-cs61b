import java.util.Set;
import java.util.HashSet;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class PostOrderDFS{
	Set<Integer> marked = new HashSet<>();

	public void poDFS(Graph g, int start){
		marked.add(start);
		for(Integer neighbor: g.adj(start)){
			if(!marked.contains(neighbor)){
				marked.add(neighbor);
			poDFS(g, neighbor);
		}
		}
		System.out.print(start + " ");
	}

	public void poDFS_store(Graph g, int start, List<Integer> items){
		marked.add(start);
		for(Integer neighbor: g.adj(start)){
			if(!marked.contains(neighbor)){
				marked.add(neighbor);
			poDFS_store(g, neighbor, items);
		}
		}
		items.add(start);
	}

	public static void main(String[] args){
		PostOrderDFS p = new PostOrderDFS();
		List<Integer> items = new ArrayList<>();
		DirectedGraph g = new DirectedGraph(6);
		g.addEdge(0, 1);
		g.addEdge(0, 3);
		g.addEdge(3, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 4);
		g.addEdge(3 ,4);
		g.addEdge(2, 5);
		g.addEdge(4, 5);
		p.poDFS_store(g, 0, items);
		for(Integer i: items){
			System.out.println(i);
		}
	}
}