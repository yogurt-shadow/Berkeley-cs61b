public class LLRB_self<Key extends Comparable<Key>> extends BST2<Key>{
	/**  my own code for LLRB (not required for the lecture). 
		Just a try for myself */

	public LLRB_self(Key key){
		super(key);
	}

	public LLRB_self(){}

	
	private Node findparent(Key key){
		if(!contains(key)){return null;}
		return findparent(root, key);
	}

	private Node findparent(Node x, Key key){
		if(x.key.equals(key)){return null;}
		if(x.left != null && x.left.key.equals(key)){
			return x;
		}
		if(x.right != null && x.right.key.equals(key)){
			return x;
		}
		if(x.key.compareTo(key) > 0){
			return findparent(x.left, key);
		}
		else{
			return findparent(x.right, key);
		}
	}

	private Node find(Key key){
		return find(root, key);
	}

	private Node find(Node x, Key key){
		if(x.key.equals(key)){return x;}
		Node p = findparent(key);
		if(p.left.key.equals(key)){return p.left;}
		else{return p.right;}
	}

	@Override
	public void insert(Key key){
		super.insert(key);
		Node parent = findparent(key);
		if(parent == null){return;}
		if(parent.left != null &&parent.left.key.equals(key)){parent.cleft = "RED";}
		if(parent.right != null && parent.right.key.equals(key)){parent.cright = "RED";}
		for(int i = 0; i < size / 2; i++){
		validate(); //implement some rules now
	}
	}

	public void validate(){
		validate1(root);
		validate2(root);
		validate3(root);
	}

	private void validate1(Node x){
		if(x == null){return;}
		if(x.cright == "RED" && x.cleft != "RED"){
			rL(x.key);
			x.cright = null;
			Node parent = findparent(x.key);
			if(parent != null && parent.left == x){
				parent.cleft = "RED";
			}
			if(parent != null && parent.right == x){
				parent.cright = "RED";
			}
		}
		else{
			validate1(x.left);
			validate1(x.right);
		}
	}

	private void validate2(Node x){
		if(x == null){return;}
		if(x.cleft == "RED" && x.left.cleft == "RED"){
			rR(x.key);
			x.cleft = null;
			Node parent = findparent(x.key);
			if(parent != null && parent.left == x){
				parent.cleft = "RED";
			}
			if(parent != null && parent.right == x){
				parent.cright = "RED";
			}
		}
		else{
			validate2(x.left);
			validate2(x.right);
		}
	}

	private void validate3(Node x){
		if(x == null){return;}
		if(x.cleft == "RED" && x.cright == "RED"){
			Node parent = findparent(x.key);
			if(parent != null && parent.left == x){
				parent.cleft = "RED";
			}
			if(parent != null && parent.right == x){
				parent.cright = "RED";
			}
			x.cleft = "BLACK";
			x.cright = "BLACK";
		}
		else{
			validate3(x.left);
			validate3(x.right);
		}
	}


	public Node rotateRight(Key key){
		Node key_owner = find(key);
		Node parent = findparent(key);
		Node replace = key_owner.left;
		key_owner.left = replace.right;
		replace.right = key_owner;
		if(parent != null && parent.left == key_owner){
			parent.left = replace;
		}
		if(parent != null && parent.right == key_owner){
			parent.right = replace;
		}
		return replace;
	}

	public Node rotateLeft(Key key){
		Node key_owner = find(key);
		Node parent = findparent(key);
		Node replace = key_owner.right;
		key_owner.right = replace.left;
		replace.left = key_owner;
		if(parent != null && parent.left == key_owner){
			parent.left = replace;
		}
		if(parent != null && parent.right == key_owner){
			parent.right = replace;
		}
		return replace;
	}
	public void rR(Key key){
		if(root == find(key)){
			root = rotateRight(key);
		}
		else {
			Node key1 = find(key);
			key1 = rotateRight(key);
		}
	}

	public void rL(Key key){
		if(root == find(key)){
			root = rotateLeft(key);
		}
		else {
			Node key1 = find(key);
			key1 = rotateLeft(key);
		}
	}

	@Override
	/**  sign the color (only red) */
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
					if(((Node) nodes[i][j]).cleft == "RED"){
						System.out.print("(left red)");
					}
					if(((Node) nodes[i][j]).cright == "RED"){
						System.out.print("(right red)");
					}
				}

			}
			if(allnull){
				System.out.print("EMPTY");
				break first;}
			System.out.println();
		}
	}


	public static void main(String[] args){
		LLRB_self<Integer> llrb = new LLRB_self<>();
		for(int i = 1; i <= 7; i++){
			llrb.insert(i);
		}
		llrb.print();

		LLRB_self<Integer> llrb2 = new LLRB_self<>();
		for(int i = 7; i >= 1; i--){
			llrb2.insert(i);
		}
		llrb2.print();

		LLRB_self<Integer> llrb3 = new LLRB_self<>();
		for(int i = 20; i >= 11; i--){
			llrb3.insert(i);
		}
		llrb3.print();
	}
}