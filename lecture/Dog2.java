public class Dog2 implements Animal{
	public void sniff(Animal a){
		System.out.print("dog sniff animal");
	}

	public void flatter(Dog2 a){
		System.out.print("u r cool dog");
	}

	public static void main(String[] args){
		Animal a = new Dog2();
		Dog2 d = new Dog2();
		a.flatter(d);

	}
}