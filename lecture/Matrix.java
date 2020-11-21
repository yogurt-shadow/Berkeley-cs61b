public class Matrix{
	private int numbers;
	public double[][] items;

	public Matrix(int n, double[][] b){
		numbers = n;
		items = b;
	}

	public void print(){
		for(int i = 0; i < numbers; i++){
			String result = "(";
			for(int j = 0; j < numbers - 1; j++){
				result += items[i][j] + ", ";
			}
			result += items[i][numbers - 1] + ")";
			System.out.println(result);
		}
	}
}