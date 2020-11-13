public class Maximizer{
	public static OurComparable max(OurComparable[] items){
		int maxdex = 0;
		for(int i = 0; i < items.length; i++){
			if(items[i].compare(items[maxdex])){
				maxdex = i;
			}
		}
		return items[maxdex];
	}

	public static void main(String[] args){
		Dog4[] dogs = {new Dog4("ada", 3), new Dog4("defe", 9),
		new Dog4("ddede", 15)};
		Dog4 maxdog = (Dog4) max(dogs);
		maxdog.bark();
	}
}