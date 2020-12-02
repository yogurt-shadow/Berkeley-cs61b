package bearmaps;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Map;
import java.util.HashMap;


public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{
	private List<PriorityNode> items;
	private int size;
	private Map<T, Integer> itemKeys; // with array index

	private class PriorityNode{
		private T item;
		private double priority;

		public PriorityNode(T item, double priority){
			this.item = item;
			this.priority = priority;
		}

		@Override
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
            	if(((PriorityNode) o).item == null){
            		return false;
				}
					return ((PriorityNode) o).item.equals(this.item);
            }
        }
	}

	public ArrayHeapMinPQ(){
		items = new ArrayList<>();
		items.add(new PriorityNode(null, 100));
		size = 0;
		itemKeys = new HashMap<>();
	}

	private int parent(int x){
		return x / 2;
	}

	private int left(int x){
		return 2 * x;
	}

	private int right(int x){
		return 2 * x + 1;
	}

	private void exchange(int a, int b){
		PriorityNode copy = items.get(a);
		itemKeys.put(copy.item, b);
		itemKeys.put(items.get(b).item, a);
		items.set(a, items.get(b));
		items.set(b, copy);
	}

	private void swimup(int x){
		if(parent(x) == 0){
			return;
		}
		if(items.get(parent(x)).priority > items.get(x).priority){
			exchange(parent(x), x);
			swimup(parent(x));
		}
	}

	private void swimdown(int x){
		if(size < left(x)) {
			return;
		}
		if(items.get(left(x)).priority < items.get(x).priority){
			exchange(left(x), x);
			swimdown(left(x));
		}
		if(size < right(x)){
			return;
		}
		else if(items.get(right(x)).priority < items.get(x).priority){
			exchange(right(x), x);
			swimdown(right(x));
		}
	}

	@Override
	public void add(T item, double priority){
		if(contains(item)){
			throw new IllegalArgumentException("the item already exsists");
		}
		size += 1;
		itemKeys.put(item, size);
		items.add(size, new PriorityNode(item, priority));
		swimup(size);
	}

	@Override
	public boolean contains(T item){
		if(size == 0){
			return false;
		}
		 return itemKeys.containsKey(item);
		}

	@Override
	public T getSmallest(){
		if(size == 0){
			throw new NoSuchElementException();
		}
		return items.get(1).item;
	}

	@Override
	public T removeSmallest(){
		if(size == 0){
			throw new NoSuchElementException();
		}
		PriorityNode smallest = items.get(1);
		T result = smallest.item;
		exchange(1, size);
		items.remove(size);
		itemKeys.remove(smallest.item);
		size -= 1;
		swimdown(1);
		return result;
	}

	@Override
	public int size(){
		return size;
	}

	@Override
	public void changePriority(T item, double priority){
		if(!contains(item)){
			throw new NoSuchElementException();
		}
		double before = items.get(itemKeys.get(item)).priority;
		items.get(itemKeys.get(item)).priority = priority;
		if(priority > before){
			swimdown(itemKeys.get(item));
		}
		else if(priority < before){
			swimup(itemKeys.get(item));
		}
	}
}