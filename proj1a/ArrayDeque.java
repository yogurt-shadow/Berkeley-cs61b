public class ArrayDeque<T>{
	private int size;
	private T[] Ts;
	private int nextfirst;
	private int nextlast;

	public ArrayDeque(){
		size = 0;
		Ts = (T[]) new Object[8];
		nextfirst = 0;
		nextlast = 1;
	}

	

	public ArrayDeque(ArrayDeque other){
		this.size = other.size;
		this.Ts = (T[]) new Object[other.Ts.length];
		System.arraycopy(other.Ts, 0, Ts, 0, other.Ts.length);
		this.nextfirst = 0;
		this.nextlast = 1;
	}

	public int size() {
		return size;
	};

	public void resize(int x){
		T[] new_home = (T[]) new Object[x];
		for(int i = 0; i < size; i++){
			new_home[i] = get(i);
		}
		nextfirst = x - 1;
		nextlast = size;
		Ts = new_home;
	}

	public void upsize(){
		resize(size * 2);
	}

	public void downsize(){
		resize(Ts.length / 2);
	}

	public boolean isFull(){
		return size == Ts.length;
	}


	public void addFirst(T i){
		if(isFull()){
			upsize();
		}
		Ts[nextfirst] = i;
		if(nextfirst == 0){
			nextfirst = Ts.length-1;
		}
		else{nextfirst--;}
		size += 1;

	}

	public void addLast(T i){
		if(isFull()){
			upsize();
		}
		Ts[nextlast] = i;
		if(nextlast == Ts.length - 1){
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

	public T removeFirst(){
		if(size *4 < Ts.length  && Ts.length > 8){
			downsize();
			if(size == 0){
			return null;
		}
		size -= 1;
		if(nextfirst == Ts.length - 1){
			nextfirst = 0;
		}
		else{
			nextfirst++;
		}
		T result = Ts[nextfirst];
			return result;
		}
		else{
		if(size == 0){
			return null;
		}
		size -= 1;
		if(nextfirst == Ts.length - 1){
			nextfirst = 0;
		}
		else{
			nextfirst++;
		}
		T result = Ts[nextfirst];
		Ts[nextfirst] = null;
		return result;}
	}

	public T removeLast() {
		if (size * 4 < Ts.length && Ts.length > 8) {
			downsize();
			if (size == 0) {
				return null;
			}
			size -= 1;
			if (nextlast == 0) {
				nextlast = Ts.length - 1;
			} else {
				nextlast--;
			}
			T result = Ts[nextlast];
			return result;
		} else {
			if (size == 0) {
				return null;
			}
			size -= 1;
			if (nextlast == 0) {
				nextfirst = Ts.length - 1;
			} else {
				nextlast--;
			}
			T result = Ts[nextlast];
			Ts[nextlast] = null;
			return result;
		}
	}

	public T get(int index){

		return Ts[(nextfirst + 1 + index) % Ts.length];
	}

	public void showlength(){
		System.out.println(Ts.length);
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