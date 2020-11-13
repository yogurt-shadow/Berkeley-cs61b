public class ShowDog extends Dog3{

	public void bark(){
		System.out.println("I'm a ShowDog!");
	}

	public ShowDog(String a, String b, int c, double d){
		super(a, b, c, d);
	}

	public static void main(String[] args){
		Object o2 = new ShowDog("M","dasd", 25, 512.2);
		
		ShowDog sdx = ((ShowDog) o2);
		sdx.bark();

		Dog3 dx = ((Dog3) o2);
		dx.bark();

		((Dog3) o2).bark();
 
	}
}