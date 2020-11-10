/**  Tests the sort class. */
import org.junit.Test;
import static org.junit.Assert.*;

public class TestSort{
	/** Test the Sort.sort method. */
	@Test
	public void testSort(){
		String[] input = {"i", "have", "an", "egg."};
		String[] expected = {"an", "egg.", "have", "i"};

		Sort.sort(input);

		assertArrayEquals(expected, input);
	}
	@Test
	public void testSwap(){
		String[] input = {"i", "have", "an", "egg."};
		int a =0;
		int b = 2;
		String[] expected = {"an", "have", "i", "egg."};
		Sort.swap(input, a ,b);
		assertArrayEquals(input, expected);
	}

	@Test
	public void testFindSmallest(){
		String[] input = {"i", "have", "an", "egg."};
		int expected = 2;

		int actual = Sort.findSmallest(input, 0);
		org.junit.Assert.assertEquals(actual, expected);

		String[] input2 = {"there", "are", "many", "pigs."};
		int expected2 = 2;


		int actual2 = Sort.findSmallest(input2, 2);
		assertEquals(expected2, actual2);
	}
	
}