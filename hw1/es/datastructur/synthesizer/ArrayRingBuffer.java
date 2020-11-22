package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T>{
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    @Override
    public int capacity(){
        return rb.length;
    }

    @Override
    public int fillCount(){
        return fillCount;
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
        for(int i = 0; i < capacity; i++){
            rb[i] = null;
        }
    }

    public int update(int x){
        if(x == capacity() - 1){
            return 0;
        }
        else{
            return x + 1;
        }
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if(isFull()){
            throw new RuntimeException("Ring buffer overflow");
        }
        else{
            rb[last] = x;
            last = update(last);
            fillCount += 1;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if(isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        else{
             T result = rb[first];
             first = update(first);
             fillCount -= 1;
             return result;
         }
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        return rb[first];
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
     @Override
    public Iterator<T> iterator(){
        return new arrayiter();
    }

    private class arrayiter implements Iterator<T>{
        private int index;

        public boolean hasNext(){
            return index < rb.length;
        }

        public T next(){
            T result = rb[index];
            index += 1;
            return result;
        }
    }

    @Override
    public boolean equals(Object o){
        if(o == this){ // same object absolutely true
            return true;
        }
        if(o == null){
            return false;
        }
        if(o.getClass() != this.getClass()){
            return false;
        }
        ArrayRingBuffer<T> other2= (ArrayRingBuffer<T>) o;
        if(other2.fillCount() != this.fillCount()){
            return false;
        }

        // Non-destructive
        ArrayRingBuffer<T> copy = new ArrayRingBuffer<>(this.capacity());
        for(T item : this){
            copy.enqueue(item);
        }

        for(T item2 : other2){
            if(item2 != copy.dequeue()){
                return false;
            }
        }
        return true;

    }
}
    // TODO: Remove all comments that say TODO when you're done.
