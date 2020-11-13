public class TestAnimals{
	public static void main(String[] args){
		Animal a = new Animal("Pluto", 10);
		Cat2 c = new Cat2("Garfield", 6);
		Dog d = new Dog("Fido", 4);

		a.greet();
		c.greet();
		d.greet();
		a = c;
		((Cat2) a).greet();
		a.greet();

		a = new Dog("Spot", 10);
		d = (Dog) a;
	}
}