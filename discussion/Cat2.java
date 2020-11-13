public class Cat2 extends Animal{
	public Cat2(String name, int age){
		super(name, age);
		this.noise = "Meow!";
	}

	@Override
	public void greet(){
		System.out.println("Cat " + name + " says: " + makeNoise());
	}

	public static void main(String[] args){
		Cat2 m = new Cat2("mao", 3);
		m.greet();
		Cat2 bigger_m = new Cat2("big_mao", 12);
		bigger_m.greet();
	}
}