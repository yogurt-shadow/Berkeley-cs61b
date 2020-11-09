public class ArrayDeque<T> {
	private int size;
	private T[] _Ts;
	private int nextfirst;
	private int nextlast;

	public ArrayDeque() {
		size = 0;
		_Ts = (T[]) new Object[8];
		nextfirst = 0;
		nextlast = 1;
	}

	

	/**public ArrayDeque(ArrayDeque other) {
		this.size = other.size;
		this._Ts = (T[]) new Object[other._Ts.length];
		System.arraycopy(other._Ts, 0, _Ts, 0, other._Ts.length);
		this.nextfirst = 0;
		this.nextlast = 1;
	}
	*/

	public int size() {
		return size;
	};

	private void resize(int x) {
		T[] home = (T[]) new Object[x];
		for (int i = 0; i < size; i++) {
			home[i] = get(i);
		}
		nextfirst = x - 1;
		nextlast = size;
		_Ts = home;
	}

	private void upsize() {
		resize(size * 2);
	}

	private void downsize() {
		resize(_Ts.length / 2);
	}

	private boolean isFull() {
		return size == _Ts.length;
	}


	public void addFirst(T i) {
		if (isFull()) {
			upsize();
		}
		_Ts[nextfirst] = i;
		if (nextfirst == 0) {
			nextfirst = _Ts.length - 1;
		}
		else {nextfirst--;}
		size += 1;
	}

	public void addLast(T i) {
		if (isFull()) {
			upsize();
		}
		_Ts[nextlast] = i;
		if (nextlast == _Ts.length - 1) {
			nextlast = 0;
		}
		else {nextlast++;}
		size += 1;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void printDeque() {
		if (this.isEmpty()) {
			System.out.println("");
		}
		else {
			for (int i = 0; i < size; i++) {
				System.out.print(this.get(i) + " ");
			}
			System.out.println("");
		}
	}

	public T removeFirst(){
		if (size *4 < _Ts.length  && _Ts.length > 8) {
			downsize();
			if (size == 0) {
				return null;
			}
			size -= 1;
			if (nextfirst == _Ts.length - 1) {
				nextfirst = 0;
			}
			else {
				nextfirst++;
			}
			T result = _Ts[nextfirst];
			return result;
		}
		else {
			if (size == 0) {
				return null;
			}
			size -= 1;
			if (nextfirst == _Ts.length - 1) {
				nextfirst = 0;
			} 
			else {
				nextfirst++;
			}
			T result = _Ts[nextfirst];
			_Ts[nextfirst] = null;
			return result;
		}
	}

	public T removeLast() {
		if (size * 4 < _Ts.length && _Ts.length > 8) {
			downsize();
			if (size == 0) {
				return null;
			}
			size -= 1;
			if (nextlast == 0) {
				nextlast = _Ts.length - 1;
			}
			else {
				nextlast--;
			}
			T result = _Ts[nextlast];
			return result;
		}
		else {
			if (size == 0) {
				return null;
			}
			size -= 1;
			if (nextlast == 0) {
				nextlast = _Ts.length - 1;
			}
			else {
				nextlast--;
			}
			T result = _Ts[nextlast];
			_Ts[nextlast] = null;
			return result;
		}
	}

	public T get(int index){
		return _Ts[(nextfirst + 1 + index) % _Ts.length];
	}

/**
	public void showlength(){
		System.out.println(_Ts.length);
	}
	*/
}
