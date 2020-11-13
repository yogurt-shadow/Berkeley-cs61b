public class HoFself{
	public static void print_larger(int x, int y, compare cc, stringify strr){
		if(cc.apply(x, y)){  strr.present(x);}
		else{ strr.present(y);}
	}

	public static void main(String[] args){
		compare cc = new bigger();
		stringify strr = new intoutput();
		print_larger(5,10, cc, strr);
	}
}