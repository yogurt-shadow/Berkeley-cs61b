public class SLList{
	private class IntNode{
		public int item;
		public IntNode next;
		public IntNode(int i, IntNode n){
			item = i;
			next = n;
		}
	}

	private IntNode first;
	public SLList(int x){
		first = new IntNode(x, null);
	}

	public void addFirst(int x){
		first = new IntNode(x, first);
	}

	public int getFirst(){
		return first.item;
	}

	public void insert(int item, int position){
		IntNode p = first;
		while(position > 0 && p.next != null){
			p = p.next;
			position -= 1;
		}
		if(position == 0) {
			IntNode copy = new IntNode(p.item, p.next);
			p.item = item;
			p.next = copy;
		}
		else{
			p.next = new IntNode(item, null);
		}
	}

	/**  reverse iteratively
	public void reverse(){
		IntNode p = first;
		int[] ele = new int[100];
		int index = 0;
		while(p != null){
			ele[index] = p.item;
			p = p.next;
			index++;
		}
		index--;
		first = new IntNode(ele[0], null);
		for(int i = 1; i <= index; i++){
			addFirst(ele[i]);
		}
	}
	*/

	/**  reverse recursively */
	public void reverse(){
		first = reverse(first);
	}

	/**  helper reverse function */
	public IntNode reverse(IntNode x){
		if(x == null || x.next == null){
			return x;
		}
		else{
			IntNode reversed = reverse(x.next);
			x.next.next = x;
			x.next = null;
			return reversed;
		}
	}




	public static void main(String[] args){
		SLList a = new SLList(2);
		a.addFirst(5);
		a.addFirst(10);
		a.addFirst(20); // 20 -> 10 -> 5 -> 2
		a.reverse();  // 2 -> 5 -> 10 -> 20
		System.out.println(a.first.item);
		System.out.println(a.first.next.next.item);
	}
}