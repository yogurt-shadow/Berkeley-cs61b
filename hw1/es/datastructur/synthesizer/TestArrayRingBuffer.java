package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
       ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
       assertEquals(true, arb.isEmpty());
       for(int i = 0; i < 8; i++){
       	arb.enqueue(i);
       }
       assertEquals(false, arb.isEmpty());
       arb.enqueue(12);
       arb.enqueue(13);
       assertEquals(true, arb.isFull());
       int a = arb.dequeue();
       assertEquals(0, a);
       arb.enqueue(100);
       int b = arb.peek();
       assertEquals(1, b);
    }
}
