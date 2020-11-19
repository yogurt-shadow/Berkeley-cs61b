public class dis06_exam{

	public static void f5(int N, int M){
		if(N < 10) return;
		for(int i = 0; i <= N % 10; i++){
			f5(N / 10, M / 10);
			System.out.println(M);
		}
	}

	public static void main(String[] args){
		f5(1299, 10);
	}
}