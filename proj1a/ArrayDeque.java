public class ArrayDeque<T> {
    
    private int size;
    private T[] iTs;
    private int nextfirst;
    private int nextlast;

    public ArrayDeque() {
        size = 0;
        iTs = (T[]) new Object[8];
        nextfirst = 0;
        nextlast = 1;
	}

	

	/**public ArrayDeque(ArrayDeque other) {
		this.size = other.size;
		this.iTs = (T[]) new Object[other.iTs.length];
		System.arraycopy(other.iTs, 0, iTs, 0, other.iTs.length);
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
	    iTs = home;
    }

    private void upsize() {
	    resize(size * 2);
    }

    private void downsize() {
	    resize(iTs.length / 2);
    }

    private boolean isFull() {
	    return size == iTs.length;
    }


	public void addFirst(T i) {
	    if (isFull()) {
	        upsize();
	    }
	    iTs[nextfirst] = i;
	    if (nextfirst == 0) {
		    nextfirst = iTs.length - 1;
	    } else { 
		    nextfirst--; 
	    }
	    size += 1;
    }

	public void addLast(T i) {
	    if (isFull()) {
		    upsize();
	    }
	    iTs[nextlast] = i;
	    if (nextlast == iTs.length - 1) {
		    nextlast = 0;
		} else { 
		    nextlast++; 
	    }
	    size += 1;
    }

    public boolean isEmpty() {
	    return size == 0;
    }

    public void printDeque() {
	    if (this.isEmpty()) {
		    System.out.println("");
	    } else {
		    for (int i = 0; i < size; i++) {
			    System.out.print(this.get(i) + " ");
		    }
		    System.out.println("");
	    }
    }

    public T removeFirst() {
	    if (size * 4 < iTs.length  && iTs.length > 8) {
		    downsize();
		    if (size == 0) {
			    return null;
		    }
		    size -= 1;
		    if (nextfirst == iTs.length - 1) {
			    nextfirst = 0;
		    } else {
			    nextfirst++;
		    }
		    T result = iTs[nextfirst];
		    return result;
	    } else {
		    if (size == 0) {
			    return null;
		    }
		    size -= 1;
		    if (nextfirst == iTs.length - 1) {
			    nextfirst = 0;
		    } else {
			    nextfirst++;
		    }
		    T result = iTs[nextfirst];
		    iTs[nextfirst] = null;
		    return result;
	    }
    }

    public T removeLast() {
	    if (size * 4 < iTs.length && iTs.length > 8) {
		    downsize();
		    if (size == 0) {
			    return null;
		    }
		    size -= 1;
		    if (nextlast == 0) {
			    nextlast = iTs.length - 1;
		    } else {
			    nextlast--;
		    }
		    T result = iTs[nextlast];
		    return result;
	    } else {
		    if (size == 0) {
			    return null;
		    }
		    size -= 1;
		    if (nextlast == 0) {
			    nextlast = iTs.length - 1;
		    } else {
			    nextlast--;
		    }
		    T result = iTs[nextlast];
		    iTs[nextlast] = null;
		    return result;
	    }
    }

    public T get(int index) {
	    return iTs[(nextfirst + 1 + index) % iTs.length];
    }

/**
    public void showlength(){
	    System.out.println(iTs.length);
    }
    */
}
