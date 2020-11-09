public class SLList<LochNess> {
	/** Note: PLEASE DO NOT MESS WITH FIRST */

	private class StuffNode{
	public LochNess item;
	public StuffNode next;


	public StuffNode(LochNess i, StuffNode n){
		item = i;
		next = n;
	}
}
	/** sentinel.next is the real first. */
	private StuffNode first;
	private int size;

	 public SLList(LochNess x){
	 	first = new StuffNode(x, null);
		size = 1;
	}

	/** adds x to the front of the list */
	public void addFirst(LochNess x){
		first = new StuffNode(x, first);
		size += 1;
	}

	/** returns first */
	public LochNess getFirst(){
		return first.item;
	}

	public void addLast(LochNess x){
		size += 1;
		StuffNode p = first;
		while(p.next != null){
			p = p.next;
		}
		p.next = new StuffNode(x, null);
	}

	
	public int size(){
		return size;
	}


	public static void main(String[] args){
		SLList<Integer> L = new SLList<>(19);
		L.addFirst(20);
		System.out.println(L.getFirst());
		
	}

}