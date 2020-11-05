public class IntList{
	public int first;
	public IntList rest;

	public IntList(int f, IntList r){
		first = f;
		rest = r;
	}

	/** return the size of the list using recursion! */
	public int size(){
		if(this.rest == null){
			return 1;
		}
		else{
			return 1 + this.rest.size();
		}
	}


	/** return the size of the list using iteration */
	public int iterativeSize(){
		int size = 1;
		IntList lst = this;
		while(lst.rest != null){
			size = size + 1;
			lst = lst.rest;
		}
		return size;
	}

	/** return the ith item of this IntList */
	public int get(int i){
		if(i == 0){
			return first;
		} 
		else{
			return rest.get(i - 1);
		}
	}

	public static IntList incrList(IntList L, int x){
		IntList lst = new IntList(L.get(L.size() - 1) + x , null);
		for(int i = 1; i < L.size(); i++){
			lst = new IntList(L.get(L.size() - 1 - i) + x, lst);
		}
		return lst;
	}

	public static IntList dincrList(IntList L, int x){
		IntList p = L;
		while(p != null){
			p.first = p.first + x;
			p = p.rest;
		}
		return L;
	}

	/**  dis02_3  */
	public static IntList square(IntList L){
		if(L.rest == null){
			return new IntList(L.first * L.first, null);
		}
		return new IntList(L.first * L.first, square(L.rest));
	}

	public static IntList squareDestructive(IntList L){
		L.first = L.first * L.first;
		if(L.rest == null){
			return L;
		}
		L.rest = squareDestructive(L.rest);
		return L;
	}

	public static void main(String[] args){
		IntList L = new IntList(1, new IntList(2, new IntList(3, null)));
		IntList a = square(L);
		System.out.println(a.get(1));
		System.out.println(L.get(1));
		squareDestructive(L);
		System.out.println(L.get(1));
	}
}