import java.util.Stack;
import java.util.Set;
import java.util.HashSet;

public class Cycle{
	public boolean containsCycle1(Graph g){
		/**
		* use dfs to detect cycle
		*/
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		Set<Integer> marked = new HashSet<>();
		while(!stack.empty()){
			int current = stack.pop();
			//System.out.println(current);
			marked.add(current);
			for(Integer neighbor: g.adj(current)){
				if(stack.contains(neighbor)){
					return true;
				}
				if(!marked.contains(neighbor)){
					stack.push(neighbor);
				}
			}
		}
		return false;

	}

	public boolean containsCycle2(Graph g){
		/**
		* use disjoint sets to detect cycle
		*/
		UnionFind uf = new UnionFind(g.V());
		for(Edge edge: g.getEdges()){
			if(uf.connected(edge.getstart(), edge.getend())){
				return true;
			}
			uf.union(edge.getstart(), edge.getend());
		}
		return false;

	}

	public static void main(String[] args){
		/**
		UndirectedGraph g = new UndirectedGraph(5);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(1, 4);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		Cycle c = new Cycle();
		System.out.println(c.containsCycle1(g));
		UndirectedGraph g1 = new UndirectedGraph(3);
		g1.addEdge(0, 1);
		g1.addEdge(1, 2);
		g1.addEdge(2, 0);
		System.out.println(c.containsCycle1(g1));

		UndirectedGraph udg = new UndirectedGraph(9);
		udg.addEdge(0, 1);
		udg.addEdge(1, 2);
		udg.addEdge(1, 4);
		udg.addEdge(2, 5);
		udg.addEdge(4, 5);
		udg.addEdge(3, 4);
		udg.addEdge(5, 8);
		udg.addEdge(6, 7);
		udg.addEdge(5, 6);
		System.out.println(c.containsCycle1(udg));

		UndirectedGraph g2 = new UndirectedGraph(5);
		g2.addEdge(0, 1);
		g2.addEdge(1, 2);
		g2.addEdge(2, 3);
		g2.addEdge(2, 4);
		System.out.println(c.containsCycle1(g2));
		 */

		UndirectedGraph g = new UndirectedGraph(5);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(1, 4);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		Cycle c = new Cycle();
		System.out.println(c.containsCycle2(g));
		UndirectedGraph g1 = new UndirectedGraph(3);
		g1.addEdge(0, 1);
		g1.addEdge(1, 2);
		g1.addEdge(2, 0);
		System.out.println(c.containsCycle2(g1));

		UndirectedGraph udg = new UndirectedGraph(9);
		udg.addEdge(0, 1);
		udg.addEdge(1, 2);
		udg.addEdge(1, 4);
		udg.addEdge(2, 5);
		udg.addEdge(4, 5);
		udg.addEdge(3, 4);
		udg.addEdge(5, 8);
		udg.addEdge(6, 7);
		udg.addEdge(5, 6);
		System.out.println(c.containsCycle2(udg));

		UndirectedGraph g2 = new UndirectedGraph(5);
		g2.addEdge(0, 1);
		g2.addEdge(1, 2);
		g2.addEdge(2, 3);
		g2.addEdge(2, 4);
		System.out.println(c.containsCycle2(g2));
	}
}