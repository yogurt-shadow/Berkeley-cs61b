import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind{

	@Test
	public void test1(){
		UnionFind u1 = new UnionFind(5);
		assertEquals(u1.connected(2, 3), false);
		assertEquals(u1.find(4), 4);
		u1.union(1, 3);
		u1.union(2, 4);
		assertEquals(u1.connected(1, 4), false);
	}

	@Test
	public void test2(){
		UnionFind c = new UnionFind(7);
		c.union(0, 1);
		c.union(1, 2);
		c.union(0, 4);
		c.union(3, 5);
		assertEquals(c.connected(2, 4), true);
		c.union(2, 4);
		assertEquals(c.connected(2, 3), false);
	}
}