public class lec19{
	public static int asciiToInt(String s){
		int result = 0;
		for(int i = 0; i < s.length(); i++){
			result = result * 126;
			result += s.charAt(i); // here char is converted to int
		}
		return result;
	}

	public static void main(String[] args){
		System.out.println(asciiToInt("e12e"));
		char a = 121;
		System.out.println(a);
	}
}