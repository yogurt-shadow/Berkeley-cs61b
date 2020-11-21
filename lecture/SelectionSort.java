import java.util.HashMap;
import java.util.Map;

public class SelectionSort{

	/**  1. find the smallest item
	     2. exchange it with the first item
	     3. repeat to the next items
	     */
	private int find(int[] x, int start){
		int result = start;
		for(int i = start; i < x.length; i++){
			if(x[result] > x[i]){
				result = i;
			}
		}
		return result;
	}

	private void exchange(int[] array, int a, int b){
		int copy = array[a];
		array[a] = array[b];
		array[b] = copy;
	}
	public int[] selection(int[] array){


		for(int i = 0; i < array.length; i++){
			int smallest = find(array, i);
			exchange(array, i, smallest);
		}
		return array;
	}


	private int[] merge(int[] array1, int[] array2){
		int start1 = 0;
		int start2 = 0;
		int[] result = new int[array1.length + array2.length];
		int index = 0;
		while(start1 != array1.length && start2 != array2.length){
			if(array1[start1] >= array2[start2]){
				result[index] = array2[start2];
				start2 += 1;
				index += 1;
			}
			else{
				result[index] = array1[start1];
				start1 += 1;
				index += 1;
			}
		}
		if(start1 == array1.length){
			for(int j = start2; j < array2.length; j++){
				result[index] = array2[j];
				index += 1;
			}
		}

		if(start2 == array2.length){
			for(int j = start1; j < array1.length; j++){
				result[index] = array1[j];
				index += 1;
			}
		}
		return result;
	}

	public int[] merge_order(int[] array){
		int[] a = new int[array.length / 2];
		int[] b = new int[(array.length +1)/ 2];
		if(array.length % 2 == 0){
			System.arraycopy(array, 0, a, 0, array.length / 2);
			System.arraycopy(array, array.length / 2, b, 0, array.length / 2);
		}
		else{
			System.arraycopy(array, 0, a, 0, array.length / 2);
			System.arraycopy(array, array.length / 2, b, 0, array.length / 2 + 1);
		}
		int[] a_ordered = selection(a);
		int[] b_ordered = selection(b);
		int[] result = merge(a_ordered, b_ordered);
		return result;
	}

	public static void main(String[] args){

		int[] array = new int[]{1, 5, 3, 8};
		SelectionSort a1 =new SelectionSort();
		int[] result = a1.selection(array);
		for(int i = 0; i < result.length; i++){
			System.out.print(result[i] + " ");
		}

		System.out.println();

		SelectionSort a2 =new SelectionSort();
		int[] array1 = new int[]{1, 4, 6, 8};
		int[] array2 = new int[]{3, 5, 7, 9};
		int[] new_array = a2.merge(array1, array2);
		for(int i = 0; i < new_array.length; i++){
			System.out.print(new_array[i] + " ");
		}

		System.out.println();

		SelectionSort a3 =new SelectionSort();
		int[] array4 = new int[]{1, 5, 3, 8,3,1,14,3,3,4};
		int[] result2 = a3.merge_order(array4);
		for(int i = 0; i < result2.length; i++){
			System.out.print(result2[i] + " ");
		}
	}
}