public class MatrixPlay{

	public static vector times(Matrix a, vector b){
		double[] result = new double[3];
		for(int i = 0; i < 3; i++){
			double sum = 0;
			for(int j = 0; j < 3; j++){
				sum += a.items[i][j] * b.items[j];
			}
			result[i] = sum;
		}
		return new vector(3, result);
	}


	public static void main(String[] args){
		double[] a1 = new double[]{1, 0, 0};
		vector a = new vector(3, a1);
		System.out.println(a.length());
		double[][] m1 = new double[][]{{5, 2, 1},{1, 4, -1},{-1, -2, 3}};
		Matrix m = new Matrix(3, m1);
		
		vector b = a;
		double[] size = new double[100];
		size[0] = b.length();
		for(int n = 1; n < 100; n++){
			b = times(m, b);
			size[n] = b.length();
		}

		for(int i = 1; i < 99; i++){
			double result = (size[i + 1] - size[i])/ size[i];
			System.out.println(size[i] + " -> " + result);
		}

	}
}