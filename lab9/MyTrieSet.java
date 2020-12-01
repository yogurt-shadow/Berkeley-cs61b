import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class MyTrieSet implements TrieSet61B{
	private Trie root;

	private class Trie{
		private boolean isKey;
		private Map<Character, Trie> children;

		public Trie(){
			isKey = false;
			children = new HashMap<>();
		}
	}

	public MyTrieSet(){
		root = new Trie();
	}

	@Override
	public void clear(){
		root = new Trie();
	}

	@Override
	public boolean contains(String key){
		Trie current = root;
		for(int i = 0; i < key.length(); i++){
			if(current.children.containsKey(key.charAt(i))){
				current = current.children.get(key.charAt(i));
			}
			else{
				return false;
			}
		}
		return current.isKey;
	}

	@Override
	public void add(String key){
		if(contains(key)){
			return;
		}
		Trie current = root;
		for(int i = 0; i < key.length(); i++){
			if(!current.children.containsKey(key.charAt(i))){
				current.children.put(key.charAt(i), new Trie());
			}
			current = current.children.get(key.charAt(i));
		}
		current.isKey = true;
	}

	private void add(Trie t, String s, List<String> list){
		if(t.isKey){
			list.add(s);
		}
		for(Character child: t.children.keySet()){
			add(t.children.get(child), s + child, list);
		}
	}

	@Override
	public List<String> keysWithPrefix(String prefix){
		List<String> result = new ArrayList<>();
		Trie current = root;
		for(int i = 0; i < prefix.length(); i++){
			if(!current.children.containsKey(prefix.charAt(i))){
				return result;
			}
			current = current.children.get(prefix.charAt(i));
		}
		if(current.isKey){result.add(prefix);};
		for(Character child: current.children.keySet()){
			add(current.children.get(child), prefix + child, result);
		}
		return result;
	}

	@Override
	public String longestPrefixOf(String key){
		List<String> prefixof = keysWithPrefix(key);
		String result = "";
		int max = 0;
		for(String s: prefixof){
			if(s.length() > max){
				result = s;
				max = s.length();
			}
		}
		return result;
	}

	public static void main(String[] args){
		MyTrieSet a = new MyTrieSet();
		a.add("apple");
		a.add("administration");
		a.add("add");
		a.add("banana");
		System.out.println(a.longestPrefixOf("a"));
	}
}
