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


    @Test
    public void someTest2() {
       ArrayRingBuffer<Integer> arb2 = new ArrayRingBuffer<>(10);
       for(int i = 0; i < 8; i++){
        arb2.enqueue(i);
       }
       
       ArrayRingBuffer<Integer> arb3 = new ArrayRingBuffer<>(10);
       for(int i = 0; i < 8; i++){
        arb3.enqueue(i);
       }

       assertEquals(true, arb2.equals(arb3));
     for(int item : arb2){
        System.out.println(item);
       }
    }
}
