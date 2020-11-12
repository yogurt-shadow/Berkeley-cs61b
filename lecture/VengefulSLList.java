public class VengefulSLList<Item> extends SLList<Item>{
	SLList<Item> deletedItems;

	public VengefulSLList(){
		deletedItems = new SLList<Item>();
	}
	public void printLostItems(){
		deletedItems.print();
	}



	@Override
	public Item removeLast(){
		Item x = super.removeLast();
		deletedItems.addLast(x);
		return x;
	}
	public static void main(String[] args){

		VengefulSLList<Integer> vsl = new VengefulSLList<>();
		vsl.addLast(1);
		vsl.addLast(5);
		vsl.addLast(10);
		vsl.addLast(13);

		vsl.removeLast();
		vsl.removeLast();

		System.out.print("The fallen are: ");
		vsl.printLostItems();
	}
}