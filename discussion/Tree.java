/**  discussion 9 exam prep */
import java.util.List;
import java.util.ArrayList;

public class Tree<T>{
	private Node root;

	private class Node{
		public T item;
		public ArrayList<Node> children;
	}

	public Node getAncestor(int k, Node target){
		List<Node> list = new ArrayList<Node>();
		ancestorHelper(root, target, list);

		// if k == 0, list.get is target
		return list.get(list.size() - k - 1);
	}

	private boolean ancestorHelper(Node current, Node y, List<Node> list){
		list.add(current);
		if(current == y){
			return true;
		}
		for(Node child: current.children){
			if(ancestorHelper(child, y, list)){
				return true;
			}
		}
		list.remove(current);
		return false;
	}
}