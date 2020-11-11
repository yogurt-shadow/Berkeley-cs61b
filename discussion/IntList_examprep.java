public class IntList_examprep{
	public int first;
	public IntList_examprep rest;

	public IntList_examprep(int i, IntList_examprep r){
		first = i;
		rest = r;
	}

	@Override
	public int hashCode() {
		return first;
	}

	/**
	 * Returns a new IntList_examprep containing the ints in ARGS. You are not
	 * expected to read or understand this method.
	 */
	public static IntList_examprep list(Integer... args) {
		IntList_examprep result, p;

		if (args.length > 0) {
			result = new IntList_examprep(args[0], null);
		} else {
			return null;
		}

		int k;
		for (k = 1, p = result; k < args.length; k += 1, p = p.rest) {
			p.rest = new IntList_examprep(args[k], null);
		}
		return result;
	}

	/**
	 * Returns true iff X is an IntList_examprep containing the same sequence of ints
	 * as THIS. Cannot handle IntList_exampreps with cycles. You are not expected to
	 * read or understand this method.
	 */
	public boolean equals(Object x) {
		if (!(x instanceof IntList_examprep)) {
			return false;
		}
		IntList_examprep L = (IntList_examprep) x;
		IntList_examprep p;

		for (p = this; p != null && L != null; p = p.rest, L = L.rest) {
			if (p.first != L.first) {
				return false;
			}
		}
		if (p != null || L != null) {
			return false;
		}
		return true;
	}

	public void skippify(){
		IntList_examprep p = this;
		int n = 1;
		while(p != null){
			IntList_examprep next = p;
			for(int j = 0; j <= n; j++){
				if(next == null){
					break;
				}
				next = next.rest;
			}
			n = n + 1;
			p.rest = next;
			p = p.rest;
		}
	}

	public static void main(String[] args){
		IntList_examprep a = new IntList_examprep(1, null);
		IntList_examprep p = a;
		for(int i = 0; i < 10; i++){
			p.first = i;
			p.rest = new IntList_examprep(1, null);
			p = p.rest;
		}
		a.skippify();
		IntList_examprep p2 = a;
		for(int i = 0; i < 4; i++){
			System.out.println(p2.first);
			p2 = p2.rest;
		}
	}
}