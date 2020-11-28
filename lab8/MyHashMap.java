import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;


public class MyHashMap<K, V> implements Map61B<K, V>{
	private int initialSize;
	private double loadFactor;
	private Set<K> keys; // to contain keys
	private int size;
	private Object[] buckets;
	private int capacity; // number of buckets

	private class Entry<K, V>{
		private K key;
		private V val;
		private Entry next;

		public Entry(K key, V val, Entry next){
			this.key = key;
			this.val = val;
			this.next = next;
		}

		private V get(K key){
			if(key.equals(this.key)){
				return this.val;
			}
			if(this.next == null){return null;}
			return (V) this.next.get(key);
		}

		private K get(int x){
			if(x == 0){return this.key;}
			if(x > 0 && this.next == null){return null;}
			return (K) this.next.get(x - 1);
		}

		private void replace(K key, V value){
			if(key.equals(this.key)){
				this.val = value;
			}
			else{
				this.next.replace(key, value);
			}
		}

		private void remove(K key){
			if(key.equals(this.key)){
				int hashcode = Math.abs(key.hashCode()) % capacity;
				buckets[hashcode] = this.next;
			}
			else{
				Entry x = this;
				while(!key.equals(x.next.key)){
					x = x.next;
				}
				x.next = x.next.next;
			}
		}
	}

	public MyHashMap(){
		initialSize = 16;
		loadFactor = 0.75;
		size = 0;
		keys = new HashSet<>();
		buckets = new Object[initialSize];
		capacity = initialSize;
	}

	public MyHashMap(int initialSize){
		this.initialSize = initialSize;
		loadFactor = 0.75;
		size = 0;
		keys = new HashSet<>();
		buckets = new Object[initialSize];
		capacity = initialSize;
	}

	public MyHashMap(int initialSize, double loadFactor){
		this.initialSize = initialSize;
		this.loadFactor = loadFactor;
		size = 0;
		keys = new HashSet<>();
		buckets = new Object[initialSize];
		capacity = initialSize;
	}

	@Override
	public void clear(){
		size = 0;
		keys = new HashSet<>();
		buckets = new Object[initialSize];
		capacity = initialSize;
	}

	@Override
	public boolean containsKey(K key){
		return keys.contains(key);
	}

	@Override
	public V get(K key){
		if(!containsKey(key)){
			return null;
		}
		int hashcode = Math.abs(key.hashCode()) % capacity;
		return (V) ((Entry) buckets[hashcode]).get(key);
	}

	@Override
	public int size(){
		return size;
	}

	private void resize(){
		Object[] keyss = new Object[size];
		Object[] values = new Object[size];
		Object[] buckets2 = new Object[capacity * 2];
		Iterator<K> iter = this.iterator();
		int i = 0;
		while(iter.hasNext()){
			keyss[i] = iter.next();
			values[i] = get((K) keyss[i]);
			i += 1;
		}
		initialSize = initialSize * 2;
		clear();
		for(int j = 0; j < keyss.length; j++){
			put((K) keyss[j], (V) values[j]);
		}
	}

	@Override
	public void put(K key, V value){
		if(containsKey(key)){
			int hashcode = key.hashCode() % capacity;
			((Entry) buckets[hashcode]).replace(key, value);
		}
		else{
			int hashcode = Math.abs(key.hashCode()) % capacity;
			Entry<K, V> x = new Entry(key, value, (Entry) buckets[hashcode]);
			buckets[hashcode] = x;
			size += 1;
			keys.add(key);
		}
		if((double) size / (double) capacity > loadFactor){
			resize();
		}
	}

	@Override
	public Set<K> keySet(){
		return keys;
	} 

	@Override
	public V remove(K key){
		if(!containsKey(key)){
			return null;
		}
		V result = get(key);
		int hashcode = Math.abs(key.hashCode()) % capacity;
		((Entry) buckets[hashcode]).remove(key);
		keys.remove(key);
		size -= 1;
		return result;
	}

	@Override
	public V remove(K key, V value){
		if(get(key).equals(value)){
			return remove(key);
		}
		else{
			return null;
		}
	}

	@Override
	public Iterator<K> iterator(){
		return new hashiter();
	}

	private class hashiter implements Iterator<K>{
		private int cur;
		private int index;
		private int bindex;

		public hashiter(){
			cur = 0;
			index = 0;
			bindex = 0;
		}

		public boolean hasNext(){
			return cur < size;
		}

		public K next(){
			if(buckets[index] == null || ((Entry) buckets[index]).get(bindex) == null){
			while( buckets[index] == null || bindex > 0){
				bindex = 0;
				index += 1;
			}
		}
			bindex += 1;
			cur += 1;
			return (K) ((Entry) buckets[index]).get(bindex - 1);
		}
	}

	public static void main(String[] args){
		MyHashMap<String, Integer> studentIDs = new MyHashMap<String, Integer>();
		for(int m = 0; m < 100; m++){
			studentIDs.put("das" + m, m);
		}
		Iterator<String> k = studentIDs.iterator();
		while(k.hasNext()){
			String mm = k.next();
			System.out.println(mm);
			System.out.println(studentIDs.get(mm));
		}
		System.out.println(studentIDs.size());
		for(int m = 0; m < 20; m++){
			studentIDs.remove("das" + m);
		}
		System.out.println(studentIDs.size());
		System.out.println(studentIDs.get("das19"));
		System.out.println(studentIDs.get("das97"));
	}
} 