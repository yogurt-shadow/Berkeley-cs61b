import org.junit.Test;
import static org.junit.Assert.*;

public class TestBubbleGrid {

	@Test
	public void testupdategrid() {
		int[][] items = new int[][]{{1, 1, 0}, {1, 0, 0}, {1, 1, 0}, {1, 1, 1}};
		BubbleGrid b = new BubbleGrid(items);
		b.updatesets(items);
		assertEquals(8, b.number());

		/**  we must consider such a situation */
		int[][] items2 = new int[][]{{1, 0, 0, 1}, {1, 0, 1, 1}, {1, 1, 0, 1}};
		BubbleGrid b2 = new BubbleGrid(items2);
		b2.updatesets(items2);
		assertEquals(8, b2.number());

		int[][] items3 = new int[][]{{1, 0, 0, 0}, {1, 0, 1, 1}, {1, 1, 0, 1}};
		BubbleGrid b3 = new BubbleGrid(items3);
		b3.updatesets(items3);
		assertEquals(4, b3.number());

		int[][] items4 = new int[][]{{1, 0, 0, 1}, {0, 0, 1, 1}, {1, 1, 0, 1}};
		BubbleGrid b4 = new BubbleGrid(items4);
		b4.updatesets(items4);
		assertEquals(5, b4.number());
	}

	@Test
	public void testpopBubbles1(){
		int[][] items1 = new int[][]{{1, 1, 0}, {1, 0, 0}, {1, 1, 0}, {1, 1, 1}};
		int[][] darts1 = new int[][]{{2, 2}, {2, 0}};
		BubbleGrid b1 =new BubbleGrid(items1);
		int[] pop1 = b1.popBubbles(darts1);
		assertArrayEquals(pop1, new int[]{0, 4});
	}

	@Test
	public void testpopBubbles2(){
		int[][] items1 = new int[][]{{1, 0, 0, 0}, {1, 1, 1, 0}};
		int[][] darts1 = new int[][]{{1, 0}};
		BubbleGrid b1 =new BubbleGrid(items1);
		int[] pop1 = b1.popBubbles(darts1);
		assertArrayEquals(pop1, new int[]{2});
	}

	@Test
	public void testpopBubbles3(){
		int[][] items1 = new int[][]{{1, 0, 0, 0}, {1, 1, 0, 0}};
		int[][] darts1 = new int[][]{{1, 1}, {1, 0}};
		BubbleGrid b1 =new BubbleGrid(items1);
		int[] pop1 = b1.popBubbles(darts1);
		assertArrayEquals(pop1, new int[]{0, 0});
	}

	 @Test
    public void testBasic() {

        int[][] grid = {{1, 0, 0, 0},
                        {1, 1, 1, 0}};
        int[][] darts = {{1, 0}};
        int[] expected = {2};

        validate(grid, darts, expected);
    }

    private void validate(int[][] grid, int[][] darts, int[] expected) {
        BubbleGrid sol = new BubbleGrid(grid);
        assertArrayEquals(expected, sol.popBubbles(darts));
    }

}