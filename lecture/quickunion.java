public class quickunion implements DisjointSets{
	private int[] items;
	private int[] size;

	public quickunion(int p){
		items = new int[p];
		size = new int[p];
		for(int i = 0; i < p; i++){
			items[i] = -1;
			size[i] = 1;
		}
	}

	/**  public used for test */
	public int root(int p){
		/**  p is the root */
		if(items[p] == -1){
			return p;
		}
		else{
			return root(items[p]);
		}
	}

	public boolean isConnected(int p, int q){
		return root(p) == root(q);
	}

	public void connect(int p, int q){
		/**  only connect when isn't connected */
		if(!isConnected(p, q)){
			int root1 = root(p);
			int root2 = root(q);
			/**  tree 1 is bigger than tree 2 */
			if(size[root1] >= size[root2]){
				size[root1] += size[root2];
				items[root2] = root1;
			}
			/**  tree 1 is smaller than tree 2 */
			if(size[root1] < size[root2]){
				size[root2] += size[root1];
				items[root1] = root2;
			}
		}
		
	}

	}