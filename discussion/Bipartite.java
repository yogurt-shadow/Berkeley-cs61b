import java.util.Set;
import java.util.HashSet;

public class Bipartite{
	public boolean is_bipartite(UndirectedGraph g){
		Set<Integer> a = new HashSet<>();
		Set<Integer> b = new HashSet<>();
		for(int i = 0; i < g.V(); i++){
			boolean a_add = true;
			boolean b_add = true;
			for(Integer amember: a){
				if(g.adj(i).contains(amember)){
					a_add = false;
					break;
				}
			}
			if(a_add){
				a.add(i);
				continue;
			}

			for(Integer bmember: b){
				if(g.adj(i).contains(bmember)){
					b_add = false;
					break;
				}
			}
			if(b_add){
				b.add(i);
				continue;
			}
			return false;
		}
		return true;
	}

	public static void main(String[] args){
		Bipartite b = new Bipartite(); 
		UndirectedGraph g = new UndirectedGraph(5);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(2, 4);
		System.out.println(b.is_bipartite(g));
	}
}