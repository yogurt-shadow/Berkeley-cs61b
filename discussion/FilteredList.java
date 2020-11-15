import java.util.*;

public class FilteredList<T> implements Iterable<T>{
	public List<T> L;
	public Predicate<T> filter;

	public FilteredList(List<T> L, Predicate<T> filter){
		this.L = L;
		this.filter = filter;
	}

	@Override
	public Iterator<T> iterator(){
		return new FilteredList_iterator();
	}

	private class FilteredList_iterator implements Iterator<T>{
		public int position;

		public FilteredList_iterator(){
			position = 0;
		}

		public boolean hasNext(){
			return position < L.size();
		}

		public T next(){
			T result = L.get(position);
			position += 1;
			if(position == L.size()) {
				return result;
			}
			while(!filter.test(L.get(position)) && position < L.size()){
				position += 1;
			}
			return result;
		}
	}

	public static void main(String[] args){
		List<String> s = new ArrayList<>();
		s.add("hello boy.");
		s.add("what's up!");
		s.add("hmm");
		s.add("hahiosds");
		System.out.println(s.size());

		filter f = new filter();
		FilteredList<String> small = new FilteredList<>(s, f);
		for(String h : small){
			System.out.println(h);
		}
	}
}