package bearmaps;

import java.util.List;

public class KDTree implements PointSet{
	private Node root;

	private class Node{
		private int depth;
		private Point point;
		private Node left;
		private Node right;

		public Node(int depth, Point point){
			this.depth = depth;
			this.point = point;
		}

		private boolean is_bigger(Point other){
			if(depth % 2 == 1){
				return this.point.getX() > other.getX();
			}
			else{
				return this.point.getY() > other.getY();
			}
		}

		private double minus(Point other){
			if(depth % 2 == 1){
				return this.point.getX() - other.getX();
			}
			else{
				return this.point.getY() - other.getY();
			}
		}
	}

	private Node add(Node x, int depth, Point point){
		if(x == null){
			return new Node(depth, point);
		}
		if(x.is_bigger(point)){
			x.left = add(x.left, depth + 1, point);
		}
		else{
			x.right = add(x.right, depth + 1, point); // point is bigger or equal
		}
		return x;
	}

	public KDTree(List<Point> points){
		for(Point point: points){
			root = add(root, 1, point);
		}
	}

	private Point nearest(Node x, Point best, Point goal, double distance){
		if(x == null){
			return best;
		}
		Point best2 = best;
		if(best == null){
			best2 = x.point;
		}
		double distance2 = Point.distance(best2, goal);
		if(Point.distance(x.point, goal) < distance2){
			best2 = x.point;
			distance2 = Point.distance(x.point, goal);
		}
		int goodside;
		if(x.is_bigger(goal)){
			goodside = -1;
		}
		else{
			goodside = 1;	// goal is bigger or equal
		}

		if(goodside < 0){
			best2 = nearest(x.left, best2, goal, distance2);
			if(Point.distance(best2, goal) < Math.pow(x.minus(goal), 2)){
				return best2;
			}
			best2 = nearest(x.right, best2, goal, distance2);
		}
		else{
			best2 = nearest(x.right, best2, goal, distance2);
			if(Point.distance(best2, goal) < Math.pow(x.minus(goal), 2)){
				return best2;
			}
			best2 = nearest(x.left, best2, goal, distance2);
		}
		return best2;

	}

	@Override
	public Point nearest(double x, double y){
		return nearest(root, null, new Point(x, y), Double.MAX_VALUE);
	}

	public static void main(String[] args){
		Point p1 = new Point(1.1, 2.2);
		Point p2 = new Point(3.3, 4.4);
		Point p3 = new Point(-2.9, 4.2);

		KDTree nn = new KDTree(List.of(p1, p2, p3));
		Point ret = nn.nearest(3.0, 4.0);
		System.out.println(ret.getX());
		System.out.println(ret.getY());
	}
}