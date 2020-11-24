import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;


import static org.junit.Assert.assertEquals;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
	private Node root;
	private int size;

	private class Node{
		private K key;
		private V value;
		private Node left, right;

		public Node(K key, V value){
			this.key = key;
			this.value = value;
		}
	}

	public BSTMap(){
		root = null;
		size = 0;
	}

	/**  some helper funtion */
	private V get(Node x, K key){
		if(x == null){ return null;}
		if(key == null){throw new IllegalArgumentException("get() key should not be null");}
		if(key.equals(x.key)){return x.value;}
		if(key.compareTo(x.key) > 0){ return get(x.right, key);}
		else{ return get(x.left, key);}
	}

	private Node put(Node x, K key, V value){
		if(x == null){ return new Node(key, value);}
		if(key.compareTo(x.key) > 0){x.right = put(x.right, key, value);}
		if(key.compareTo(x.key) < 0){x.left = put(x.left, key, value);}
		if(key.equals(x.key)){x.value = value;}
		return x;
	}

	private void printInOrder(Node x){
		if(x.left != null){
			printInOrder(x.left);
		}
		System.out.println(x.key);
		if(x.right != null){
			printInOrder(x.right);
		}
	}

	public void printInOrder(){
		if(root != null){
			printInOrder(root);
		}
	}

	private void input(Set<K> keyset, Node x){
		if(x.left != null){
			input(keyset, x.left);
		}
		keyset.add(x.key);
		if(x.right != null){
			input(keyset, x.right);
		}
	}

	private Node findparent(Node x, K key){
		if(x.left != null && key.equals(x.left.key)){
			return x;
		}
		if(x.right != null && key.equals(x.right.key)){
			return x;
		}
		if(key.compareTo(x.key) > 0){
			return findparent(x.right, key);
		}
		else{
			return findparent(x.left, key);
		}
	}

	private Node find(Node x, K key){
		if(key.equals(x.key)){
			return x;
		}
		if(key.compareTo(x.key) > 0){
			return find(x.right, key);
		}
		else{
			return find(x.left, key);
		}
	}

	/**  here we know that x must have two children */
	/**  we use the largest at the left */
	private Node findreplaced(Node x){
		return largest(x.left);
	}

	private Node largest(Node x){
		if(x.right == null){return x;}
		else{
			return largest(x.right);
		}
	}
	

	@Override
	public void clear(){
		root = null;
		size = 0;
	}

	@Override
	public boolean containsKey(K key){
		return get(key) != null;
	}

	@Override
	public V get(K key){
		if(root == null){
			return null;
		}
		return get(root, key);
	}

	@Override
	public int size(){
		return size;
	}

	@Override
	public void put(K key, V value){
		if(root == null){
			root = new Node(key, value);
			size += 1;
		}
		else{
			if(!containsKey(key)){size += 1;}
			root = put(root, key, value);
		}
	}

	@Override
	public Set<K> keySet(){
		Set<K> keys = new HashSet<>();
		if(root == null){return keys;}
		input(keys, root);
		return keys;
	}

	@Override
	public Iterator<K> iterator(){
		return new BSTiter(root, size);
	}

	private class BSTiter implements Iterator<K>{
		private Node x;
		private int size;
		private Object[] keys;
		private int index;
		private int curr_index;

		public BSTiter(Node root, int s){
			x = root;
			size = s;
			keys = new Object[s];
			index = 0;
			curr_index = 0;
			fill();
		}

		/**  fill the keys */
		private void fill(){
			fillnode(x);
		}

		private void fillnode(Node root){
			if(root != null){
				if(root.left != null){
					fillnode(root.left);
				}
				keys[index] = root;
				index += 1;
				if(root.right != null){
					fillnode(root.right);
				}
			}
		}

		public boolean hasNext(){
			return curr_index < size;
		}

		public K next(){
			Node result = (Node) keys[curr_index];
			K result2 = result.key;
			curr_index += 1;
			return result2;
		}
	}

	@Override
	public  V remove(K key){
		if(!containsKey(key)){
			return null;
		}
		size -= 1;
		if(key.equals(root.key) && root.left == null){
			V result = root.value;
			root = root.right;
			return result;
		}

		if(key.equals(root.key) && root.right == null){
			V result = root.value;
			root = root.left;
			return result;
		}
		// find that node's parent.
		if(!key.equals(root.key)){
		Node x = findparent(root, key);
		// find that node.
		Node y = find(root, key);
		int i;
		if(x.left == y){i = -1;}
		else{i = 1;}
		V result = y.value;

		/**  case 1. no children */
		if(y.left == null && y.right == null) {
			if (i == -1) {
				x.left = null;
			} else {
				x.right = null;
			}
		}

		/**  case 2. one child */
		if(y.left == null && y.right != null){
			if(i == -1){ x.left = y.right;}
			else{x.right = y.right;}
		}

		if(y.left != null && y.right == null){
			if(i == -1){ x.left = y.left;}
			else{x.right = y.left; }
		}
			return result;
	}

		/**  case 3. two children */
		else{

			Node y = find(root, key);
			V result = y.value;
			Node replaced = findreplaced(y);
			K replaced_key = replaced.key;
			V replaced_value = replaced.value;
			remove(replaced_key);
			y.key = replaced_key;
			y.value = replaced_value;
			return result;

		}

	}

	@Override
	public V remove(K key, V value){
		if(get(key) == value){
			return remove(key);
		}
		else{
			return null;
		}
	}

	public static void main(String[] args) {
        BSTMap<String, Integer> bstMap = new BSTMap<>();
        for (int i = 0; i < 10; i++) {
            bstMap.put("hi" + i, 1 + i);
        }
        Iterator<String> itr = bstMap.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }

}