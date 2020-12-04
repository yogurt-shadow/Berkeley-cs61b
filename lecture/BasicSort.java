public class BasicSort {
	/**
	 * Selection sort is included in Sort.java
	 * Basic Sort includes 1. bubblesort  2. mergesort  3. insertion sort  4. shell's sort
	 */ 

	
    public int[] bubblesort(int[] b) {
        int[] a = new int[b.length];
        System.arraycopy(b, 0, a, 0, a.length);
        int length = a.length;
        int j = 0;
        while (j < length - 1) {
            for (int i = 0; i + 1 < length - j; i++) {
                if (a[i] > a[i + 1]) {
                    int copy = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = copy;
                }
            }
            j += 1;
        }
        return a;
    }

    public int[] merge(int[] left, int[] right) {
        int index = 0;
        int index1 = 0;
        int index2 = 0;
        int[] result = new int[left.length + right.length];
        while (index1 < left.length || index2 < right.length) {
            if (index1 >= left.length) {
                result[index] = right[index2];
                index2 += 1;
                index += 1;
            } else if (index2 >= right.length) {
                result[index] = left[index1];
                index1 += 1;
                index += 1;
            } else if (left[index1] > right[index2]) {
                result[index] = right[index2];
                index2 += 1;
                index += 1;
            } else {
                result[index] = left[index1];
                index1 += 1;
                index += 1;
            }
        }
        return result;
    }

    public int[] mergesort(int[] b) {
        if (b.length == 1) {
            int[] copy = new int[1];
            System.arraycopy(b, 0, copy, 0, 1);
            return copy;
        }
        int[] left = new int[(b.length + 1) / 2];
        int[] right = new int[b.length / 2];
        System.arraycopy(b, 0, left, 0, (b.length + 1) / 2);
        System.arraycopy(b, (b.length + 1) / 2, right, 0, (b.length) / 2);
        int[] left_sort = mergesort(left);
        int[] right_sort = mergesort(right);
        return merge(left_sort, right_sort);
    }

    private void insert(int[] a, int insertone, int index, int number){
    	for(int i = number; i > index; i--){
    		a[i] = a[i - 1];
    	}
    	a[index] = insertone;
    }

    public int[] insertsort(int[] b){
    	int[] result = new int[b.length];
    	result[0] = b[0];
    	for(int i = 1; i < b.length; i++){
    		for(int j = 0; j < i; j++){
    			if(result[j] > b[i]){
    				insert(result, b[i], j, i);
    				break;
    			}
				result[i] = b[i];
    		}
    	}
    	return result;
    }

    private int[] shellsort(int[] a, int increment){
    	if(increment == 1){
    		return insertsort(a);
    	}
    	int[] result = new int[a.length];
    	for(int i = 0; i < increment; i++){
    		int index = 0;
    		int j = i;
    		int[] store = new int[a.length];
    		while(j < a.length){
    		store[index] = a[j];
    		j += increment;
    		index += 1;	
    	}
    	int[] copy = new int[index];
    	System.arraycopy(store, 0, copy, 0, index);
    	int[] current = insertsort(copy);
    	int m = i; int index2 = 0;
    	while(m < result.length){
    	result[m] = current[index2];
    	m += increment; index2 += 1;
    }
    	}
    	return result;
    }

    // use (length / 3) + 1 to split
    public int[] shellsort(int[] b){
    	int increment = b.length / 3 + 1;
    	int[] result = new int[b.length];
    	System.arraycopy(b, 0, result, 0, b.length);
    	for(int i = increment; i >= 1; i--){
    		result = shellsort(result, i);
    	}
    	return result;
    }

    public static void main(String[] args) {
        BasicSort bs = new BasicSort();
        int[] test = new int[]{12, 8, 5, 7, 14, 1, 6, 2};
        /**
         int[] test_result = bs.bubblesort(test);
         for(int i = 0; i < test_result.length; i++){
         System.out.println(test_result[i]);
         }
         System.out.println();
         for(int i = 0; i < test.length; i++){
         System.out.println(test[i]);
         }
		 */

		 /**
		int[] test_result = bs.mergesort(test);
		for(int i = 0; i < test_result.length; i++){
			System.out.println(test_result[i]);
		}
		System.out.println();
		for(int i = 0; i < test.length; i++){
			System.out.println(test[i]);
		}
		*/

		/**
		int[] test_result = bs.insertsort(test);
		for(int i = 0; i < test_result.length; i++){
			System.out.println(test_result[i]);
		}
		System.out.println();
		for(int i = 0; i < test.length; i++){
			System.out.println(test[i]);
		}
		*/

		int[] test_result = bs.shellsort(test);
		for(int i = 0; i < test_result.length; i++){
			System.out.println(test_result[i]);
		}
		System.out.println();
		for(int i = 0; i < test.length; i++){
			System.out.println(test[i]);
		}
    }
}