private class BST<Key>{
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

	public static BST find(BST T, Key sk){
		if(T == null){
			return null;
		}
		if(sk.equals(T.key)){
			return T;
		}
		if(sk > T.key){
			return find(T.right, sk);
		}
		else{
			return find(T.left, sk);
		}
	}

	public static BST insert(BST T, Key ik){
		
			if(T == null){
				return new BST(ik);
			}
			if(T.key > ik){
				return insert(T.left, ik);
			}
			if(T.key < ik){
				return insert(T.right, ik);
			}
			else{
				return T;
			}
	}

	
}