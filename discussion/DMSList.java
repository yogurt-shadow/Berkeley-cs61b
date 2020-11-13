public class DMSList{
	private IntNode sentinel;
	public DMSList(){
		sentinel = new IntNode(-1000, new LastIntNode());
	}

	public class IntNode{
	public int item;
	public IntNode next;

	public IntNode(int i, IntNode n){
		item = i;
		next = n;
	}

	public int max(){
		return Math.max(item, next.max());
	}
}

public class LastIntNode extends IntNode{
	public LastIntNode(){
		super(0, null);
	}
	@Override
	public int max(){
		return 0;
	}
}


public int max(){
	return sentinel.next.max();
}

}