public class ArrayDeque<Item>{
	public int size;
	public Item[] items;
	public int nextfirst;
	public int nextlast;

	public ArrayDeque(){
		size = 0;
		items = (Item[]) new Object[8];
		nextfirst = 0;
		nextlast = 1;
	}

	public ArrayDeque(ArrayDeque other){
		this.size = other.size;
		this.items = (Item[]) new Object[other.items.length];
		System.arraycopy(other.items, 0, items, 0, other.items.length);
		this.nextfirst = 0;
		this.nextlast = 1;
	}

	public int size() {
		return size;
	};

	public void resize(int x){
		Item[] new_home = (Item[]) new Object[x];
		for(int i = 0; i < size; i++){
			new_home[i] = get(i);
		}
		nextfirst = x - 1;
		nextlast = size;
		items = new_home;
	}

	public void upsize(){
		resize(size * 2);
	}

	public void downsize(){
		resize(items.length / 2);
	}

	public boolean isFull(){
		return size == items.length;
	}


	public void addFirst(Item i){
		if(isFull()){
			upsize();
		}
		items[nextfirst] = i;
		if(nextfirst == 0){
			nextfirst = items.length-1;
		}
		else{nextfirst--;}
		size += 1;

	}

	public void addLast(Item i){
		if(isFull()){
			upsize();
		}
		items[nextlast] = i;
		if(nextlast == items.length - 1){
			nextlast = 0;
		}
		else{nextlast++;}
		size += 1;
	}

	public boolean isEmpty(){
		return size == 0;
	}

	public void printDeque(){
		if(this.isEmpty()){
			System.out.println("");
		}
		else{
			for(int i = 0; i < size; i++){
				System.out.print(this.get(i) + " ");
			}
			System.out.println("");
		}
	}

	public Item removeFirst(){
		if(size / items.length < 0.25 && items.length > 8){
			downsize();
		}
		if(size == 0){
			return null;
		}
		size -= 1;
		if(nextfirst == items.length - 1){
			nextfirst = 0;
		}
		else{
			nextfirst++;
		}
		Item result = items[nextfirst];
		items[nextfirst] = null;
		return result;
	}

	public Item removeLast(){
		if(size * 4 <= items.length && items.length > 8){
			downsize();
		}
		if(size == 0){
			return null;
		}
		size -= 1;
		if(nextlast == 0){
			nextlast = items.length- 1;
		}
		else{nextlast--;}
		Item result = items[nextlast];
		items[nextlast] = null;
		return result;
	}

	public Item get(int index){
		return items[(nextfirst + 1 + index) % items.length];
	}

	public void showlength(){
		System.out.println(items.length);
	}

	public static void main(String[] args){
		ArrayDeque<Integer> a = new ArrayDeque<>();
		for(int i = 0; i <= 1000; i++){
			a.addLast(i);
		}
		a.printDeque();
		for(int i = 0; i <= 996; i++){
			a.removeLast();
		}
		a.printDeque();
		a.showlength();
		System.out.println(a.size);
	}
}