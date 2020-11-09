public class Arrays{
	public static int[] insert(int[] arr, int item, int position){

		int[] results = new int[arr.length + 1];
		if(position >= arr.length){
			System.arraycopy(arr, 0, results, 0, arr.length);
			results[arr.length] = item;
			return results;
		}
		else{
			System.arraycopy(arr, 0, results, 0, position);
			results[position] = item;
			System.arraycopy(arr, position, results, position + 1, arr.length - position);
			return results;
		}
	}

	/**  destructively */
	public static void reverse(int[] arr){
		int[] copy = new int[arr.length];
		System.arraycopy(arr, 0, copy, 0, arr.length);
		for(int i = 0; i < arr.length; i++){
			arr[i] = copy[arr.length - i - 1];
		}
	}

	public static int[] replicate(int[] arr){
		int sum = 0;
		for(int i = 0; i < arr.length; i++){
			sum += arr[i];
		}
		int[] results = new int[sum];
		int j = 0;
		for(int i = 0; i < arr.length; i++){
			for(int index = j; index < j + arr[i]; index++){
				results[index] = arr[i];
			}
			j = j + arr[i];
		}
		return results;
	}


	public static void main(String[] args){
		int[] a = {1, 2, 3, 4};
		int[] b = replicate(a);
		for(int i = 0; i < b.length; i++){
			System.out.println(b[i]);
		}
		
	}
}