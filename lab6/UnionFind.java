public class UnionFind {

    // TODO - Add instance variables?
    private int[] items;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
       items = new int[n];
       for(int i = 0; i < n; i++){
       	items[i] = -1;  // -1 means that size is 1 (oneself)
       }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        // TODO
        if(vertex < 0 || vertex >= items.length){
        	throw new IllegalArgumentException("You give a" + vertex + ", Index is out of range.");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        if(items[v1] < 0){  // v1 is the root
        	return -items[v1];
        }
        else{
        	return sizeOf(items[v1]);  // find its parent
        }
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        return items[v1];
    }


    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        if(!connected(v1, v2)) {
            int root1 = find(v1);
            int root2 = find(v2);
            if (sizeOf(v1) > sizeOf(v2)) {
                items[root2] = root1;
                items[root1] -= 1;
            } else {
                items[root1] = root2;
                items[root2] -= 1;
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        if(items[vertex] < 0){  // vertex is the root
        	return vertex;
        }
        else{
        	int result = find(parent(vertex));
        	items[vertex] = result;  // path-compression
        	return result;
        }
    }

}
