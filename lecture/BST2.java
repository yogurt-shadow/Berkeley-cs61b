public class BST2<Key extends Comparable<Key>>{
	protected Node root;
	protected int size;

	protected class Node{
		protected Key key;
		protected Node left;
		protected Node right;
		protected  String cleft;
		protected  String cright;

		public Node(Key k){
			key = k;
		}
	}

	public BST2(){
		size = 0;
	}

	public BST2(Key key){
		root = new Node(key);
		size = 1;
	}

	public int size(){
		return size;
	}

	public boolean isEmpty(){
		return size == 0;
	}

	private boolean contains(Node x, Key key){
		if(x == null){
			return false;
		}
		if(x.key.equals(key)){
			return true;
		}
		if(x.key.compareTo(key) > 0){
			return contains(x.left, key);
		}
		else{
			return contains(x.right, key);
		}
	}

	public boolean contains(Object key) {
		if(size == 0){return false;}
		if (key.getClass() != root.key.getClass()) {
			return false;
		} else {
			return contains(root,(Key) key);
		}
	}

	private Node insert(Node x, Key key){
		if(x == null){
			return new Node(key);
		}
		else if(x.key.compareTo(key) > 0){
			x.left = insert(x.left, key);
		}
		else{
			x.right = insert(x.right, key);
		}
		return x;
	}

	public void insert(Key key){
		if(contains(key)){
			return;
		}
		size += 1;
		root = insert(root, key);
	}

	public void print(){
		Object[][] nodes = new Object[1000][1000];
		if(root == null){return;}
		nodes[0][0] = root;
		int index = 0;
		first1: for(int height = 0; height < size; height++){
		for(int j = 0; j < size; j++){
			if(nodes[height][j] != null){
				nodes[height + 1][index] = ((Node) nodes[height][j]).left;
				nodes[height + 1][index + 1] =((Node) nodes[height][j]).right;
				index += 2;
				}
			}
			index = 0;
			boolean nochildren = true;
			for(int i = 0; i < 1000; i++){
				if(nodes[height + 1][i] != null){
					nochildren = false;
				}
			}
			if(nochildren){break first1;}
		}
		System.out.println();
		first: for(int i = 0; i < 1000; i++){
			boolean allnull = true;
			System.out.print("the " + (i + 1) + " line:");
			for(int j = 0; j < 1000; j++){
				if(nodes[i][j] != null){
					allnull = false;
					System.out.print(" " + ((Node) nodes[i][j]).key);
				}

			}
			if(allnull){
				System.out.print("EMPTY");
				break first;}
			System.out.println();
		}
	}

	public static void main(String[] args){
		BST2<Integer> bst3 = new BST2<>();
		bst3.insert(5);
		bst3.insert(2);
		bst3.insert(4);
		bst3.insert(6);
		bst3.print();
	}
}