package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class KDTreeTest {
	private static Random r = new Random(500);

	@Test
	public void RandomTest0() {
		Point p1 = new Point(2.2, 0);
		Point p2 = new Point(2.5, 4);
		Point p3 = new Point(-3, 3.8);
		Point p4 = new Point(2.1, 3.7);
		KDTree kd = new KDTree(List.of(p1, p2, p3, p4));
		assertEquals(p4, kd.nearest(2.1, 4));
	}

	@Test
	public void RandomTest(){
		List<Point> points = buildrandom(10000);
		List<Point> targets = buildrandom(1000);
		NaivePointSet n1 = new NaivePointSet(points);
		KDTree n2 = new KDTree(points);

		for(Point target: targets){
			assertEquals(n1.nearest(target.getX(), target.getY()), n2.nearest(target.getX(), target.getY()));
		}
	}

	private List<Point> buildrandom(int capacity){
		List<Point> points = new ArrayList<>();
		for(int i = 0; i < capacity; i++){
			double x = r.nextDouble() * 20000 -10000;
			double y = r.nextDouble() * 20000 - 10000;
			points.add(new Point(x, y));
		}
		return points;
	}


	@Test
	public void TestSpeed() {
		/**
		 * for 10000000 points, running time: 30+ vs 0
		 *
		 * here, 10,000 queries and 100,000 points
		 * CS61B staff: 65-85x faster
		 * My own Test (Try it out): 90x or more
		 */
		List<Point> points = buildrandom(100000);
		List<Point> targets = buildrandom(10000);

		NaivePointSet n1 = new NaivePointSet(points);
		KDTree n2 = new KDTree(points);

		long start1 = System.currentTimeMillis();
		for(Point target: targets){
			n1.nearest(target.getX(), target.getY());
		}
		long end1 = System.currentTimeMillis();
		System.out.println("Total time elapsed: " + (end1 - start1)/1000.0 +  " seconds. (naive)");


		long start2 = System.currentTimeMillis();
		for(Point target: targets){
			n2.nearest(target.getX(), target.getY());
		}
		long end2 = System.currentTimeMillis();
		System.out.println("Total time elapsed: " + (end2 - start2)/1000.0 +  " seconds. (kdt)");
	}
}
