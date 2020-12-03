public class Edge{
		private int weight;
		private int start; 
		private int end;

		public Edge(int start, int end, int weight){
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		public int getstart(){
			return start;
		}

		public int getend(){
			return end;
		}

		public int getweight(){
			return weight;
		}
	}