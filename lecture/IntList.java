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

	public static void main(String[] args){
			IntList L = new IntList(15, null);
			L = new IntList(10, L);
			L = new IntList(5, L);

			System.out.println(L.size());
			System.out.println(L.iterativeSize());
			System.out.println(L.get(1));
			IntList w = incrList(L, 2);
			System.out.println(w.get(2));
			IntList www = dincrList(L, 4);
			System.out.println(www.get(2));
			System.out.println(www.get(1));
	}
}