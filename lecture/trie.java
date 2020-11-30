import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class trie{
	private Node root;
	private int size;

	private class Node{
		private HashMap<Character, Node> next;
		private boolean isKey;

		public Node(boolean isKey){
			this.isKey = isKey;
			next = new HashMap<>();
		}
	}

	public trie(){
		root = new Node(false);
		size = 0;
	}

	private boolean contains(Node x, char s){
		return x.next.containsKey(s);
	}

	public boolean contains(String x){
		Node current = root;
		for(int i = 0; i < x.length(); i++){
			if(!contains(current, x.charAt(i))){
				return false;
			}
			if(i == x.length() - 1){
				return current.next.get(x.charAt(i)).isKey;
			}
			current = current.next.get(x.charAt(i));
		}
		return false;
	}

	public int size(){
		return size;
	}

	public void insert(String x){
		if(contains(x)){
			return;
		}
		size += 1;
		Node current = root;
		for(int i = 0; i < x.length(); i++){
			if(contains(current, x.charAt(i))){
				if(i == x.length() - 1){
					current.next.get(x.charAt(i)).isKey = true;
				}
				current = current.next.get(x.charAt(i));
			}
			else{
				if(i == x.length() - 1){
				current.next.put(x.charAt(i), new Node(true));
			}
			else{
				current.next.put(x.charAt(i), new Node(false));
			}
				current = current.next.get(x.charAt(i));
			}
			}
		}

		private Node find(String x){
			if(!contains(x)){
				return null;
			}
			Node current = root;
			for(int i = 0; i < x.length(); i++){
				current = current.next.get(x.charAt(i));
				if(i == x.length() - 1){
					return current;
				}
			}
			return null;
		}

		public void delete(String x){
			if(!contains(x)){
				return;
			}
			size -= 1;
			Node current = find(x);
			current.isKey = false;
		}

		private void helpcollect(String x, Set<String> result, Node current){
			if(current.isKey){
				result.add(x);
			}
			for(Character y: current.next.keySet()){
				helpcollect(x + y, result, current.next.get(y));
			}
		}

		public Set collect(){
			Set<String> result = new HashSet<>();
			for(Character x : root.next.keySet()){
				 helpcollect(String.valueOf(x), result, root.next.get(x));
			}
			return result;
		}

		public Set withPrefix(String x){
			Node current = find(x);
			Set<String> result = new HashSet<>();
			if(current.isKey){
				result.add(x);
			}
			for(Character m: current.next.keySet()){
				helpcollect(x + m, result, current.next.get(m));
			}
			return result;
		}

		public static void main(String[] args){
			trie a = new trie();
			a.insert("apple");
			System.out.println(a.contains("apple"));
			System.out.println(a.contains("appl"));
			a.insert("app");
			a.insert("banana");
			System.out.println(a.contains("app"));

			//System.out.println(a.contains("app"));
			System.out.println(a.size());
			Set<String> aset = a.collect();
			System.out.println();
			for(String x: aset){
				System.out.println(x);
			}

			System.out.println();
			Set<String> app = a.withPrefix("app");
			for(String y: app){
				System.out.println(y);
			}
		}
	}