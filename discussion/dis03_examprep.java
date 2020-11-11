public class dis03_examprep{
	public static int[] flatten(int[][] x){
		int totalLength = 0;

		for(int i = 0; i < x.length; i++){
			totalLength += x[i].length;
		}

		int[] a = new int[totalLength];
		int aIndex = 0;

		for(int i = 0; i < x.length; i++){
			for(int j = 0; j < x[i].length; j++){
				a[aIndex] = x[i][j];
				aIndex++;
			}
		}
		return a;
	}

	public static void main(String[] args){
		int[][] x = {{1, 2, 3}, {}, {7, 8}};
		int[] a = flatten(x);
		for(int i = 0; i < a.length; i++){
			System.out.println(a[i]);
		}
	}
}