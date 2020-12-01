/**  discussion 9 exam prep */
import java.util.Map;
import java.util.HashMap;

public class Contacts{

	private class Node{
		public int isKey;
		public Map<Character, Node> children;

		public Node(){
			isKey = 0;
			children = new HashMap<Character, Node>();
		}
	}
	

		Node root;

		public Contacts(){root = new Node();}
	
	public void addName(String name){
		Node current = root;
		for(int i = 0; i < name.length(); i++){
			if(!current.children.containsKey(name.charAt(i))){
				Node n = new Node();
				current.children.put(name.charAt(i), n);
			}
			current = current.children.get(name.charAt(i));
			if(i == name.length() - 1){current.isKey = 1;} 
		}

	}

	public int countPartial(String partial){
		Node current = root;
		for(int i = 0; i < partial.length(); i++){
			if(current.children.containsKey(partial.charAt(i))){
				current = current.children.get(partial.charAt(i));
			}
			else{
					return 0;
				}
		}
		return current.children.size() + current.isKey;
	}

	public static void main(String[] args){
		Contacts a = new Contacts();
		a.addName("apple");
		a.addName("app");
		System.out.println(a.countPartial("app"));
		a.addName("a");
		System.out.println(a.countPartial("app"));
	}
}
