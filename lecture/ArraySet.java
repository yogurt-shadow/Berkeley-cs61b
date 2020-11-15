import java.lang.reflect.Array;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;


public class ArraySet<T> implements Iterable<T>{
	private T[] values;
	private int size;


	public ArraySet(){
		values = (T[]) new Object[100];
		size = 0;
	}

	public boolean contains(T x){
		for(int i = 0; i < size; i++){
			if(values[i].equals(x)){ return true; }
		}
		return false;
	}

	public void add(T x){
		if(x == null){
			//throw new IllegalArgumentException("You can't add null.");
			return;
		}
		if(!contains(x)){
			values[size] = x;
			size = size + 1;
		}
	}

	public int size(){ return size; }

	public Iterator<T> iterator(){
		return new ArraySetIterator();
	}
	private class ArraySetIterator implements Iterator<T>{
		private int wizPos;

		public ArraySetIterator(){
			wizPos = 0;
		}

		public boolean hasNext(){
			return wizPos < size;
		}

		public T next(){
			T item = values[wizPos];
			wizPos += 1;
			return item;
		}
		}

		/**
		@Override
		public String toString(){
			StringBuilder s = new StringBuilder("{");
			for(int i = 0; i < size - 1; i++){
				s.append(values[i].toString());
				s.append(", ");
			}
			s.append(values[size - 1].toString());
			s.append("}");
			return s.toString();
		}
		*/

		@Override
		public String toString(){
			List<String> listOfItems = new ArrayList<>();
			for(T x: this){
				listOfItems.add(x.toString());
			}
			return "{" + String.join(", ", listOfItems) + "}";
		}

	@Override
	public boolean equals(Object other2){
		if(other2 == this){return true;}
		if(other2 == null){return false;}
		if(other2.getClass() != this.getClass()){ return false;}
		ArraySet<T> other = (ArraySet<T>) other2;
		if(this.size() != other.size()){
			return false;
		}
		for( T i: this){
			if(!other.contains(i)){return false;}
		}

		return true;
	}

	public static <H> ArraySet<H> of(H... stuff){
		ArraySet<H> returnset = new ArraySet<>();
		for(H x : stuff){
			returnset.add(x);
		}
		return returnset;

	}


	public static void main(String[] args){

		ArraySet<Integer> aset = new ArraySet<>();
		aset.add(5);
		aset.add(23);
		aset.add(42);

		System.out.println(aset);

		ArraySet<Integer> aset2 = new ArraySet<>();
		aset2.add(5);
		aset2.add(23);
		aset2.add(42);

		System.out.println(aset.equals(aset2));
		System.out.println(aset.equals("fish"));
		System.out.println(aset.equals(null));

		ArraySet<String> aset3 = ArraySet.of("hi", "I'm", "here");
		System.out.println(aset3);

		/**
		Iterator<Integer> aseer = aset.iterator();
		while(aseer.hasNext()){
			System.out.println(aseer.next());
		}

		for(int i : aset){
			System.out.println(i);
		}
		*/

		/**
		Set<Integer> javaset = new HashSet<>();
		javaset.add(5);
		javaset.add(23);
		javaset.add(42);

		Iterator<Integer> seer = javaset.iterator();
		while(seer.hasNext()){
			int i = seer.next();
			System.out.println(i);
		}

		for(int i : javaset){
			System.out.println(i);
		}*/
	}
}