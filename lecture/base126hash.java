public class base126hash{
	public static int string2hash(String s){
		int hash = 0;
		for(int i = 0; i < s.length(); i++){
			hash = hash * 126;
			hash += (int) s.charAt(i);
		}
		return hash % 50;
	}

	public static String findsamehash(String s){
		int i = string2hash(s);
		String r = "";
		first: for(char r1 = 97; r1 <= 126; r1++){
			second: for(char r2 = 97; r2 <= 126; r2++){
				third: for(char r3 = 97; r3 <= 126; r3++){
					r = String.valueOf(r1) + String.valueOf(r2) + String.valueOf(r3);
					if(string2hash(r) == i){
						System.out.println(r);
						break first;
					}
				}
			}
		}
		return r;
	}

	public static void main(String[] args){
		System.out.println(string2hash("aa"));
		System.out.println(string2hash("ada"));
		findsamehash("aa");
	}
}