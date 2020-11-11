public class IntList_sans{
	public int first;
	public IntList_sans rest;
	public IntList_sans(int f, IntList_sans r){
		first = f;
		rest = r;
	}

	/** non-destructively creates a copy of x that contains no occurences of y.*/
	public static IntList_sans ilsans(IntList_sans x, int y){
		if(x == null ){ return null; }
		if(x.first == y){return ilsans(x.rest, y); }
		return new IntList_sans(x.first, ilsans(x.rest, y));
	}

	/** destructively. */
	public static IntList_sans dilsans(IntList_sans x, int y){
		if(x == null){ return null; }
		if(x.first == y){ 
			return dilsans(x.rest, y);
		}
		x.rest = dilsans(x.rest, y);
		return x;
	}
	
}