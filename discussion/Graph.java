import java.util.Set;

public interface Graph{

	void addEdge(int u, int v);
	int V();
	int E();
	boolean connected(int u, int v);
	Set<Integer> adj(int u);
	Set<Edge> getEdges();
}