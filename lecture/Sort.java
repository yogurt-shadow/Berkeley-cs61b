public class Sort{
    public static void sort(String[] s){
        //Find the smallest item.
        //Move it to the front.
        //Selection sort the rest(recursion)
    	sort(s, 0);
    }

    /** sorts x starting at position start. */
    private static void sort(String[] x, int start){
    	if(start == x.length){
    		return;
    	}
    	int smallest = findSmallest(x, start);
    	swap(x, start, smallest);
    	sort(x, start + 1);
    }


    /** Swap item a with b. */
    public static void swap(String[] x, int a, int b){
    	String copy = x[a];
    	x[a] = x[b];
    	x[b] = copy;
    }

    public static int findSmallest(String[] x, int start){
    	int smallestIndex = start;
    	for(int i = start; i < x.length; i++ ){
    		if(x[i].compareTo(x[smallestIndex]) < 0){
    			smallestIndex = i;
    		}

    	}
        return smallestIndex;
    }
}