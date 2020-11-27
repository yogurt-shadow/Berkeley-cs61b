public class BST<Key extends  Comparable<Key>>{
	private Key key;
	private BST left;
	private BST right;

	public BST(Key key, BST left, BST right){
		this.key = key;
		this.left = left;
		this.right = right;
	}

	public BST(Key key){
		this.key = key;
	}

	public  BST find(BST T, Key sk){
		if(T == null){
			return null;
		}
		if(sk.equals(T.key)){
			return T;
		}
		if(T.key.compareTo(sk) < 0){
			return find(T.right, sk);
		}
		else{
			return find(T.left, sk);
		}
	}

	public  BST insert(BST T, Key ik){
		
			if(T == null){
				return new BST(ik);
			}
			if(T.key.compareTo(ik) > 0){
				return insert(T.left, ik);
			}
			if(T.key.compareTo(ik) < 0){
				return insert(T.right, ik);
			}
			else{
				return T;
			}
	}

	
}