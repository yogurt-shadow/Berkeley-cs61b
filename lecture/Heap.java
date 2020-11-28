public class Heap<Item extends Comparable<Item>>{
	private Object[] keys;
	/**  we start from 1 */
	/**    1
	     2   3
	   4  5 6  7  */
	private int size;

	public Heap(){
		keys = new Object[100];
		size = 0;
	}

	public Item getsmallest(){
		if(size == 0){return null;}
		return (Item) keys[1];
	}

	public void insert(Item x){
		keys[size + 1] = x;
		size += 1;
		swimup(size);
	}

	private int parent(int x){
		return x / 2;
	}

	private void exchange(int a, int b){
		Item copy = (Item) keys[a];
		keys[a] = keys[b];
		keys[b] = copy;
	}

	private void swimup(int x){
		if(parent(x) == 0){
			return;
		}
		if(((Item) (keys[parent(x)])).compareTo( (Item) keys[x]) > 0){
			exchange(parent(x), x);
		}
		swimup(parent(x));
	}

	public Item removeMin(){
		Item result = getsmallest();
		exchange(1, size);
		keys[size] = null;
		size -= 1;
		swimdown(1);
		return result;
	}

	private int left(int x){
		return 2 * x;
	}

	private int right(int x){
		return 2 * x + 1;
	}

	private void swimdown(int x){
		if(left(x) > size){return;}
		if( ((Item) keys[left(x)]).compareTo((Item) keys[x]) < 0){
			exchange(x, left(x));
			swimdown(left(x));
		}
		else if(right(x) > size){return;}
		if(((Item) keys[right(x)]).compareTo((Item) keys[x]) < 0){
			exchange(x, right(x));
			swimdown(right(x));
		}
	}

	public void print(){
		System.out.println();
		int i = 1;
		for(int index = 1; index <= size; index++){
		if(index == i){
			System.out.print("the " + i + " line: ");
			i = i * 2;
		}
		System.out.print(keys[index] + " ");
		if(index + 1 == i){
			System.out.println();
		}
		}
	}

	public static void main(String[] args){
		/** Heap<Integer> heap = new Heap<>();
		for(int i = 6; i >= 1; i--){
			heap.insert(i);
		}
		heap.print();

		System.out.println();

		for(int i = 0; i < 1; i++){
			heap.removeMin();
		}
		heap.print();*/
		Heap<Character> h = new Heap<>();
		h.insert('f');
		h.print();
		h.insert('h');
		h.print();
		h.insert('d');
		h.print();
		h.insert('b');
		h.print();
		h.insert('c');
		h.print();
		h.removeMin();
		h.print();
		h.removeMin();
		h.print();
	}
}