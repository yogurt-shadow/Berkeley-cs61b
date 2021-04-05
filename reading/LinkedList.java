public class LinkedList<Item>{
	private int size;
	private Item[] items;

	public LinkedList(){
		size = 0;
		items = (Item[]) new Object[100];
	}

	public void add(Item x){
		items[size] = x;
		size += 1;
	}

}