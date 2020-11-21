public class vector{
		public int numbers;
		public double[] items;

		public vector(int n, double[] b){
			numbers = n;
			items = b;
		}

		public double length(){
			double squares = 0;
			for(int i = 0; i < numbers; i++){
				squares += items[i] * items[i];
			}
			return Math.sqrt(squares);
		}

		public void print(){
			String result = "{";
			for(int i = 0; i < numbers - 1; i++){
				result += items[i] + ", ";
			}
			result += items[numbers - 1] + "}";
			System.out.println(result);
		}
	}