package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Random;
import java.util.ArrayList;

public class ArrayHeapMinPQTest {

	@Test
	public void TestSmallest(){
	ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
	for(int i = 0; i < 100; i++){
		pq.add("hello" + i, i);
	}
	assertEquals("hello0", pq.removeSmallest());
	pq.changePriority("hello99", -2);
	assertEquals("hello99", pq.removeSmallest());
	assertEquals(98, pq.size());
}

	@Test
	public void TestMore(){
		ArrayHeapMinPQ<Character> pq2= new ArrayHeapMinPQ<>();
		for(char x = 97; x <= 126; x++){
			pq2.add(x, (double) 1 / (int) x);
		}
		assertEquals(126, (int) pq2.getSmallest());
		int i = 126;
		while(pq2.size() > 0){
			assertEquals(i, (int) pq2.removeSmallest());
			i = i - 1;
		}
	}

	@Test
	public void TestMore2(){
		ArrayHeapMinPQ<Character> pq2= new ArrayHeapMinPQ<>();
		for(char x = 97; x <= 126; x++){
			pq2.add(x, (double) 1 / (int) x);
		}
		for(char x = 97; x <= 126; x++){
			pq2.changePriority(x, (double) x / 1);
		}
		int i = 97;
		while(pq2.size() > 0){
			assertEquals(i, (int) pq2.removeSmallest());
			i += 1;
		}
	}

	@Test
	public void RandomTest(){
		Random r = new Random();
		ArrayHeapMinPQ<Integer> pq1 = new ArrayHeapMinPQ<>();
		NaiveMinPQ<Integer> pq2 = new NaiveMinPQ<>();
		ArrayList<Integer> p = new ArrayList<>();
		for(int i = 0; i < 2000; i++){
			int ran1 = r.nextInt(10000);
			int priority = r.nextInt(3000);
			if(pq2.contains(ran1)){
				assertEquals(true, pq1.contains(ran1));
				continue;
			}
			if(p.contains(priority)){
				continue;
			}
			p.add(priority);
			pq1.add(ran1, priority);
			pq2.add(ran1, priority);
		}
		while(pq1.size() > 0){
			assertEquals(pq1.removeSmallest(), pq2.removeSmallest());
			assertEquals(pq1.size(), pq2.size());
		}
	}

	@Test
	public void RandomTest2(){
		Random r = new Random();
		ArrayHeapMinPQ<Integer> pq1 = new ArrayHeapMinPQ<>();
		NaiveMinPQ<Integer> pq2 = new NaiveMinPQ<>();
		ArrayList<Integer> p = new ArrayList<>();
		for(int i = 0; i < 2000; i++){
			int ran1 = r.nextInt(10000);
			int priority = r.nextInt(3000);
			if(pq2.contains(ran1)){
				assertEquals(true, pq1.contains(ran1));
				continue;
			}
			if(p.contains(priority)){
				continue;
			}
			p.add(priority);
			pq1.add(ran1, priority);
			pq2.add(ran1, priority);
		}
		for(int j = 0; j < 2000; j++){
			int ran2 = r.nextInt(10000);
			int priority = r.nextInt(2000);
			if(p.contains(priority)){
				continue;
			}
			if(pq1.contains(ran2)){
				pq1.changePriority(ran2, priority);
				pq2.changePriority(ran2, priority);
				p.add(priority);
			}
		}
		while(pq1.size() > 0){
			assertEquals(pq1.removeSmallest(), pq2.removeSmallest());
			assertEquals(pq1.size(), pq2.size());
		}
	}
}
