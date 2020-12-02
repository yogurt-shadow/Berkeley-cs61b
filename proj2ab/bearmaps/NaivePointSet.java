package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet{
	private List<Point> points;

	public NaivePointSet(List<Point> points){
		this.points = points;
	}

	@Override
	public Point nearest(double x, double y){
		Point goal = new Point(x, y);
		Point result = null;
		double distance = Double.MAX_VALUE;
		for(Point point: points){
			if(Point.distance(point, goal) < distance){
				distance = Point.distance(point, goal);
				result = point;
			}
		}
		return result;
	}

}
