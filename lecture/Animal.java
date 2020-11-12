public interface Animal{
	default void greet(Animal a){
		System.out.print("hello animal");
	}

	default void sniff(Animal a){
		System.out.print("sniff animal");
	}

	default void flatter(Animal a){
		System.out.print("u r cool animal");
	}
}