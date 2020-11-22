public class BubbleGrid{
	private int[][] grid;
	private UnionFind sets;
	private int row_num;
	private int col_num;

	public BubbleGrid(int[][] items){
		grid = items;
		/**  width times height */
		row_num = items.length;
		col_num = items[0].length;
		/**  here we add a new one to calculate the number of existing bubbles */
		sets = new UnionFind(row_num * col_num + 1);
	}

	/**  helps to convert 2D grid coordinate to sets index */
	private int setsindex(int a, int b){
		return a * col_num + b;
		}

	/**  used to help update sets based on current grid */
	/**  here items is another grid */
	public void updatesets(int[][] items){
		/** union all topmost bubbles with our new one */
		for(int i = 0; i < col_num; i++){
			/**  items[0] means the topmost row */
			if(items[0][i] == 1){
				sets.union(setsindex(0, i), row_num * col_num);
			}
		}

		/**  union other bubbles */
		/** here we start with the second row (index 1) */

		/**  take note that I use two loops here */
		/**  the first loop considers left and above situation */
		for(int r = 1; r < row_num; r++){
			for(int c = 0; c < col_num; c++){

				if(items[r][c] == 1){
				/**  in case that c - 1 out of index */
				if(c == 0){
					if(sets.connected(row_num * col_num, setsindex(r - 1, 0))){
						sets.union(row_num * col_num, setsindex(r, c));
					}
				}

				else{
					if(sets.connected(row_num * col_num, setsindex(r - 1, c)) || sets.connected(row_num * col_num, setsindex(r, c - 1))){
						sets.union(row_num * col_num, setsindex(r, c));
					}
				}
			}
			}
		}

		/**  the second loop considers the right situation */
		/* ex. 1 0 0 1
		       1 0 1 1
		       then the bubble (1, 2) won't be calculated by the first loop */
		for(int rr = row_num - 1; rr >= 1; rr--){
			for(int cc = col_num - 1; cc >= 0; cc--){

				if(items[rr][cc] == 1){
				/**  in case that c - 1 out of index */
				if(cc == col_num - 1){
					if(sets.connected(row_num * col_num, setsindex(rr - 1, cc))){
						sets.union(row_num * col_num, setsindex(rr, cc));
					}
				}

				else{
					if(sets.connected(row_num * col_num, setsindex(rr - 1, cc)) || sets.connected(row_num * col_num, setsindex(rr, cc + 1))){
						sets.union(row_num * col_num, setsindex(rr, cc));
					}
				}
			}
			}
		}


	}

	/**  set the unstuck bubbles zero */
	public void setzero(){
		for(int i = 0; i < row_num; i++){
			for(int j = 0; j < col_num; j++){
				if(!sets.connected(row_num * col_num, setsindex(i, j))){
					grid[i][j] = 0;
				}
			}
		}
	}

	/**  return number of stuck bubbles */
	public int number(){
		return sets.sizeOf(row_num * col_num) - 1;
	}


	public int[] popBubbles(int[][] darts){
		int[] pop_num = new int[darts.length];
		for(int i = 0; i < darts.length; i++){
			int row = darts[i][0];
			int col = darts[i][1];

			if(grid[row][col] == 0){
				pop_num[i] = 0;
				continue;  // nothing happens
			}


			else{
				updatesets(grid);
				int past_num = number();
				grid[row][col] = 0;
				/**  renew sets */
				sets = new UnionFind(col_num * row_num + 1);
				updatesets(grid);
				int current_num = number();
				setzero();
				pop_num[i] = past_num - current_num - 1;
			}
			}
			return pop_num;
		}
		
		
	}