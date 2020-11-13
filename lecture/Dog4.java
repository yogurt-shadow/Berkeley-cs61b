public class Dog4 implements OurComparable<Dog4>{
	private String owner;
	private int size;

	public Dog4(String a, int n){
		owner =a;
		size = n;
	}

	public void bark(){
		System.out.println("I am " + owner);
	}

	public boolean compare(Dog4 other){
		return this.size > other.size;
	}

	public static void main(String[] args){
		Dog4 a = new Dog4("dda", 5);
		Dog4 b = new Dog4("rfrefr", 18);
		System.out.println(a.compare(b));
	}
}