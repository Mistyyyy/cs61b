package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     * @param cap the received size of arrayBuffer
     */
    public ArrayRingBuffer(int cap) {
        rb = (T[]) new Object[cap];
        first = -1;
        last = -1;
        fillCount = 0;
        capacity = cap;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (isEmpty()) {
            first = 0;
        }
        if (!isFull()) {
            last = (last + 1) % capacity();
            rb[last] = x;
            fillCount++;
        } else {
            throw new RuntimeException("Ring Buffer Overflow");
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        if (!isEmpty()) {
            T res = rb[first];
            first = (first + 1) % capacity;
            fillCount--;
            return res;
        }
        throw new RuntimeException("Ring Buffer Underflow");
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        if (!isEmpty()) {
            return rb[first];
        }
        throw new RuntimeException("Ring Buffer Underflow");
    }

    private class KeyIterator implements Iterator<T> {
        private int pos;
        private int count;

        private KeyIterator() {
            pos = first;
            count = 0;
        }

        @Override
        public boolean hasNext() {
            if (count >= fillCount) {
                return false;
            }
            return true;
        }

        @Override
        public T next() {
            if (!isEmpty()) {
                count++;
                T res = rb[pos];
                pos = (pos + 1) % capacity;
                return res;
            }
            return null;
        }
    }

    public Iterator<T> iterator() {
        return new KeyIterator();
    }
}
