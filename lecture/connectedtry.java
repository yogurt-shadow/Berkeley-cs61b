public class connectedtry implements DisjointSets{
	private int[] items;

	/**  Theta(1) */
	public connectedtry(int size){
		items = new int[size];
		for(int i = 0; i < size; i++){
			items[i] = i;
		}
	}

	/**  Theta(1) */
	public boolean isConnected(int p, int q){
		return items[p] == items[q];
	}

	/**  Theta(N) */
	public void connect(int p, int q){
		for(int i = 0; i < items.length; i++){
			if(isConnected(i, q)){
				items[i] = items[p];
			}
		}
	}

	public static void main(String[] args){
		connectedtry c = new connectedtry(7);
		c.connect(0, 1);
		c.connect(1, 2);
		c.connect(0, 4);
		c.connect(3, 5);
		System.out.println(c.isConnected(2, 4));
		System.out.println(c.isConnected(3, 0));
		c.connect(4, 2);
		c.connect(4, 6);
		c.connect(3, 6);
		System.out.println(c.isConnected(3, 0));
	}
}