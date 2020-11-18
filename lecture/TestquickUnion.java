import org.junit.Test;
import static org.junit.Assert.*;

public class TestquickUnion{

	@Test
	public void testroot(){
		quickunion a = new quickunion(6);
		assertEquals(a.root(5), 5);
		assertEquals(a.root(2), 2);
	}

	@Test
	public void testconnect1(){
		quickunion c = new quickunion(7);
		c.connect(0, 1);
		c.connect(1, 2);
		c.connect(0, 4);
		c.connect(3, 5);
		assertEquals(c.isConnected(2, 4), true);
		assertEquals(c.isConnected(3, 0), false);
		c.connect(4, 2);
		c.connect(4, 6);
		c.connect(3, 6);
		assertEquals(c.isConnected(6, 5), true);
	}

	@Test
	public void testconnect2(){
		quickunion c2 = new quickunion(6);
		c2.connect(1, 2);
		c2.connect(5,4);
		assertEquals(c2.isConnected(5, 3), false);
		assertEquals(c2.isConnected(0, 5), false);
		c2.connect(3, 5);
		assertEquals(c2.isConnected(3, 0), false);
		c2.connect(0, 1);
		c2.connect(1, 5);
		assertEquals(c2.isConnected(2, 3), true);
	}
}