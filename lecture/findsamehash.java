import javax.print.DocFlavor;

public class findsamehash{
	public static void main(String[] args){
		String a = "abc";
		first: for(char b1 = 1; b1 <= 126; b1++){
		second:	for(char b2 = 1; b2 <= 126; b2++){
		third:	for(char b3 = 1; b3 <= 126; b3++) {
				String b = String.valueOf(b1) + String.valueOf(b2) + String.valueOf(b3);
				if (b.hashCode() == a.hashCode()) {
					System.out.println(b);
					System.out.println(b.hashCode());
					System.out.println(a.hashCode());
					System.out.println();
				}
			}
			}
		}
		}
}