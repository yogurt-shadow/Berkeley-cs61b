public class LinkedListDeque<T> implements Deque<T> {

    private IntNode first;
    private IntNode last;
    private int size;

    private class IntNode {

        private T item;
        private IntNode next;
        private IntNode prev;

        public IntNode(T i, IntNode n, IntNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    public LinkedListDeque() {
        first = null;
        last = null;
        size = 0;
    }


    private LinkedListDeque copy() {
        LinkedListDeque<T> result = new LinkedListDeque<T>();
        for (int j = 0; j < this.size; j++) {
            result.addLast((T) this.get(j));
        }
        return result;
    }

    @Override
    public void addFirst(T x) {
        first = new IntNode(x, first, null);
        size += 1;
        if (first.next != null) {
            first.next.prev = first;
        }
        if (last == null) {
            last = first;
        }
    }

    @Override
    public void addLast(T x) {
        last = new IntNode(x, null, last);
        size += 1;
        if (last.prev != null) {
            last.prev.next = last;
        }
        if (first == null) {
            first = last;
        }
    }

    @Override
    public boolean isEmpty() {
        return (first == null && last == null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if (this.isEmpty()) {
            System.out.println("");
        } else {
            IntNode p = first;
            while (p != last) {
                System.out.print(p.item + " ");
                p = p.next;
            }
            System.out.print(p.item);
            System.out.println("");
        }
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        } else {
            size -= 1;
            T result = first.item;
            first = first.next;
            if (first == null) {  //empty now!
                last = null;
                return result;
            }
            first.prev = null;
            return result;
        }
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        } else {
            size -= 1;
            T result = last.item;
            last = last.prev;
            if (last == null) {  // empty now!
                first = null;
                return result;
            }
            last.next = null;
            return result;
        }
    }

    @Override
    public T get(int index) {
        int i = index;
        IntNode p = first;
        while (i != 0) {
            p = p.next;
            i--;
        }
        return p.item;
    }

    /**
     * helper function. helps to get a new LinkedList removed first
     */
    private LinkedListDeque getremove() {
        LinkedListDeque b = this.copy();
        b.removeFirst();
        return b;
    }

    public T getRecursive(int index) {
        if (index == 0) {
            return first.item;
        } else {
            return (T) this.getremove().getRecursive(index - 1);
        }
    }
}
