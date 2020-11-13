public class SList{
	public class IntNode{
		public int item;
		public IntNode next;
		public IntNode(int i, IntNode n){
			item = i;
			next = n;
		}
	}

	private static IntNode sentinel;
	public SList(){
		sentinel = new IntNode(982734, null);
	}

	public void insertFront(int x){
		sentinel.next = new IntNode(x, sentinel.next);
	}

	public int getFront(){
		if(sentinel.next == null){ return -1;}
		return sentinel.next.item;
	}
}