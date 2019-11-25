package synthesizer;
import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {
    /**
     *
     * @return the size of the buffer.
     */
    int capacity();

    /**
     *
     * @return the number of items currently in the buffer.
     */
    int fillCount();

    /**
     *
     * @param x add item x to the end of the buffer.
     */
    void enqueue(T x);

    /**
     *
     * @return delete the item and return of the front of the buffer.
     */
    T dequeue();

    /**
     *
     * @return return and not delete the front of the buffer.
     */
    T peek();

    Iterator<T> iterator();

    /**
     *
     * @return the buffer is or not empty.
     */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /**
     *
     * @return the buffer is or not full.
     */
    default boolean isFull() {
        return fillCount() == capacity();
    }
}
