import org.junit.Test;
import static org.junit.Assert.*;

public class TestBST2{

	@Test
	public void testContains(){
		BST2<Integer> bst = new BST2<>(5);
		assertTrue(bst.contains(5));
		assertFalse(bst.contains(12));
		assertFalse(bst.contains("happy"));
		BST2<Integer> bst2 = new BST2<>();
		assertFalse(bst2.contains(4));
	}

	@Test
	public void testInsert(){
		BST2<Integer> bst3 = new BST2<>();
		for(int i = 1; i <= 5; i++){
			bst3.insert(i);
		}
		assertEquals(5, bst3.size());
		assertTrue(bst3.contains(4));
	}
}