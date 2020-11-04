/** The DogLauncher class will 'test dirve' the Dog class */
public class DogLauncher{

  public static void main(String[] args){
    Dog d = new Dog(51);
    Dog d2 = new Dog(100);
    Dog bigger = d.maxDog(d2);
    bigger.makenoise();
    System.out.println(Dog.binomen);
    Dog bigger2 = Dog.maxDog(d, d2);
    bigger2.makenoise();
  //  d.makenoise();
  }
}
