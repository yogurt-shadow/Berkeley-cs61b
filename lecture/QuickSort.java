public class QuickSort{
	public int[] quicksort(int[] a){
		if(a.length == 1 || a.length == 0){
			return a;
		}
		int pivot = a[0];
		int index1 = 0;
		int index2 = 0;
		int[] bigger = new int[a.length];
		int[] copy = new int[a.length];
		for(int i = 1; i < a.length; i++){
			if(a[i] < pivot){
				copy[index1] = a[i];
				index1 += 1;
			}
			else{
				bigger[index2] = a[i];
				index2 += 1;
			}
		}
		int[] left = new int[index1];
		int[] right = new int[index2];
		System.arraycopy(copy, 0, left, 0, index1);
		System.arraycopy(bigger, 0, right, 0, index2);
		int[] left2 = quicksort(left);
		int[] right2 = quicksort(right);
		int[] result = new int[a.length];
		System.arraycopy(left2, 0, result, 0, index1);
		result[index1] = pivot;
		System.arraycopy(right2, 0, result, index1 + 1, index2);
		return result;
	}

	public static void main(String[] args){
		QuickSort qs = new QuickSort();
		int[] a = new int[]{1000, 4, 4, 8, 2, 6, 9, 324, 23, 543, 1, 5, 545, 14};
		int[] b = qs.quicksort(a);
		for(int i = 0; i < b.length; i++){
			System.out.print(b[i] + " ");
		}
		System.out.println();
		int[] a2 = new int[]{32, 432,643,23,1,4454,42,235,235,325,35,3,523,5};
		int[] b2 = qs.quicksort(a2);
		for(int i = 0; i < b2.length; i++){
			System.out.print(b2[i] + " ");
		}
	}
}