import org.junit.Test;
import static org.junit.Assert.*;
public class TestSList{
	@Test
	public void test(){
		SList s1 = new SList();
		SList s2 = new SList();
		s1.insertFront(1);
		s2.insertFront(2);
		assertNotEquals(s1.getFront(), s2.getFront());
		assertEquals(1, s1.getFront());
	}
}