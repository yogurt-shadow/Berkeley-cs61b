public class kdtree{
	private Node root;

	private static class Body{
		private double x;
		private double y;
		public Body(double x, double y){
			this.x = x;
			this.y = y;
		}
	}

	private static class Node{
		private Body body;
		private Node left_down;
		private Node right_up;

		public Node(Body x){
			body = x;
		}
	}

	private Node insert(Node n, Body x, int depth){
		if(n == null){
			return new Node(x);
		}
		if(depth % 2 == 1){ // left or right
			if(x.x >= n.body.x){
				n.right_up = insert(n.right_up, x, depth + 1);
			}
			else{
				n.left_down = insert(n.left_down, x, depth + 1);
			}
		}
		else{
			if(x.y >= n.body.y){
				n.right_up = insert(n.right_up, x, depth + 1);
			}
			else{
				n.left_down = insert(n.left_down, x, depth + 1);
			}
		}
		return n;
	}

	private double dist(Body x, Body y){
		return Math.sqrt((x.x - y.x) * (x.x - y.x) + (x.y - y.y) * (x.y - y.y));
	}

	public Body nearest(Body x){
		return nearest(root, x, null, 1);
	}

	private Body nearest(Node n, Body goal, Body best, int depth){
		if(n == null){
			return best;
		}
		Body best2 = best;
		if(best == null){
			best2 = n.body;
		}
		else if(dist(n.body, goal) < dist(best, goal)){
			best2 = n.body;
		}
		int good_side;
		if(depth % 2 == 1){
			if(goal.x < n.body.x){
				good_side = -1;
			}
			else{good_side = 1;}
		}
		else{
			if(goal.y < n.body.y){
				good_side = -1;
			}
			else{good_side = 1;}
		}
		Body result;
		if(good_side == 1){
		 result = nearest(n.right_up, goal, best2, depth + 1);
		 if((depth % 2 == 1 && dist(goal, result) > Math.abs(goal.x - n.body.x)) || (depth % 2 == 0 && dist(goal, result) > Math.abs(goal.y - n.body.y))){
		 result = nearest(n.left_down, goal, result, depth + 1);
		}
	}
	else{
		result = nearest(n.left_down, goal, best2, depth + 1);
		if((depth % 2 == 1 && dist(goal, result) > Math.abs(goal.x - n.body.x)) || (depth % 2 == 0 && dist(goal, result) > Math.abs(goal.y - n.body.y))){
		result = nearest(n.right_up, goal, result, depth + 1);
	}
	}
	return result;
	}

	private boolean contains(Node n, double x, double y, int depth){
		if(n == null){
			return false;
		}
		if(n.body.x == x && n.body.y == y){
			return true;
		}
		if(depth % 2 == 1){
			if(x >= n.body.x){
				return contains(n.right_up, x, y, depth + 1);
			}
			else{
				return contains(n.left_down, x, y, depth + 1);
			}
		}
		else{
			if(y >= n.body.y){
				return contains(n.right_up, x, y, depth + 1);
			}
			else{
				return contains(n.left_down, x, y, depth + 1);
			}
		}
	}

	public void insert(Body x){
			root = insert(root, x, 1);
	}

	public boolean contains(double x, double y){
		return contains(root, x, y, 1);
	}

	public static void main(String[] args){
		kdtree kd = new kdtree();
		kd.insert(new Body(2, 3));
		kd.insert(new Body(1, 5));
		kd.insert(new Body(4, 2));
		kd.insert(new Body(4, 5));
		kd.insert(new Body(3, 3));
		kd.insert(new Body(4, 4));
		kd.insert(new Body(2.1, 6));
		System.out.println(kd.nearest(new Body(1.9, 6)).x);
		System.out.println(kd.nearest(new Body(1.9, 6)).y);
	}

}