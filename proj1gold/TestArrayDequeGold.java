import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold{

	@Test
	public void testremove(){
	StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
	ArrayDequeSolution<Integer> sad2 = new ArrayDequeSolution<>();
	for(int i = 0; i < 10; i++){
		double number = StdRandom.uniform();

		if(number < 0.5){ sad1.addFirst(i); sad2.addFirst(i);}
		else{ sad1.addLast(i);sad2.addLast(i);}
	}

	for(int i = 0; i < 10; i++){
		double number = StdRandom.uniform();
		if(number < 0.5){
			int remove1 = sad1.removeFirst();
			int remove2 = sad2.removeFirst();
			assertEquals("removeFirst():", remove1, remove2);
		}
		else{
			int remove1 = sad1.removeLast();
			int remove2 = sad2.removeLast();
			assertEquals("removeLast():", remove1, remove2);
		}
	}
}

@Test
	public void testsize(){
	StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
	ArrayDequeSolution<Integer> sad2 = new ArrayDequeSolution<>();
	for(int i = 0; i < 10; i++){
		double number = StdRandom.uniform();

		if(number < 0.5){ sad1.addFirst(i); sad2.addFirst(i);}
		else{ sad1.addLast(i);sad2.addLast(i);}
	}

	int size1 = sad1.size();
	int size2 = sad2.size();
	assertEquals("size():", size1, size2);
}

@Test
	public void testget(){
	StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
	ArrayDequeSolution<Integer> sad2 = new ArrayDequeSolution<>();
	for(int i = 0; i < 10; i++){
		double number = StdRandom.uniform();

		if(number < 0.5){ sad1.addFirst(i); sad2.addFirst(i);}
		else{ sad1.addLast(i);sad2.addLast(i);}
	}

	for(int i = 0; i < 10; i++){
		Integer size1 =  sad1.get(i);
		Integer size2 = sad2.getRecursive(i);
		assertEquals("get():",size1, size2);
	}
}


}

