package es.datastructur.synthesizer;
import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T>{
	int capacity();           // return size
	int fillCount();          // return number of items currently
	void enqueue(T x);        // add item x to the end
	T dequeue();              // delete and return item from the front
	T peek();                 // return item from the front
	


	default boolean isEmpty(){
		return fillCount() == 0;
	}

	default boolean isFull(){
		return capacity() == fillCount();
	}
}