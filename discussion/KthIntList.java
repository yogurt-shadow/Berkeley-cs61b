import java.util.Iterator;
import java.util.NoSuchElementException;

public class KthIntList implements Iterator<Integer>{
	public int k;
	private IntList curList;
	private boolean hasNext;

	public KthIntList(IntList I, int k){
		this.k = k;
		this.curList = I;
		this.hasNext = true;
	}

	public boolean hasNext(){
		return this.hasNext;
	}

	public Integer next(){
		if(curList == null){
			throw new NoSuchElementException();
		}
		int result = curList.first;
		int step = 0;
		while(step < k){
			step += 1;
			curList = curList.rest;
			if(curList == null){
				hasNext = false;
				break;
			}
		}
		return result;
	}

	public static void main(String[] args){
		IntList L = new IntList(1, new IntList(2, new IntList(3, new IntList(6, new IntList(12, null)))));
		for(Iterator<Integer> p = new KthIntList(L, 1); p.hasNext();){
			System.out.println(p.next());
		}
	}
}