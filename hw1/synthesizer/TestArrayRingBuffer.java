package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void dequeueTest() {
        int size = 100;
        int adderNumber = 2019;
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<Integer>(size);
        for (int i = 0; i < size; i++) {
            arb.enqueue(i + adderNumber);
        }
        for (int i = 0; i < size - 1; i++) {
            int value = arb.dequeue();
            assertEquals(i + adderNumber, value);
        }
        int finals = arb.dequeue();
        assertEquals(finals, size - 1 + adderNumber);
    }

    @Test
    public void peekTest() {
        int size = 100;
        int specNumber = 766;
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<Integer>(size);
        for (int i = 0; i < size; i++) {
            arb.enqueue(i + specNumber);
            int value = arb.peek();
            assertEquals(specNumber, value);
        }
        for (int i = 0; i < size - 1; i++) {
            arb.dequeue();
            int value = arb.peek();
            assertEquals(specNumber + i + 1, value);
        }
    }

    @Test
    public void fillEmptyTest() {
        int size = 10;
        ArrayRingBuffer<String> arb = new ArrayRingBuffer<String>(size);
        assertTrue(arb.isEmpty());
        int i = 0;
        for (; i < size; i++) {
            arb.enqueue("aaa");
        }
        assertTrue(arb.isFull());
        arb.dequeue();
        assertFalse(arb.isFull());
        assertFalse(arb.isEmpty());
        for (i = 0; i < size - 1; i++) {
            arb.dequeue();
        }
        assertTrue(arb.isEmpty());
    }

    @Test
    public void countCapacityEnqueueTest() {
        int size = 100;
        ArrayRingBuffer<String> arb = new ArrayRingBuffer<String>(size);
        assertEquals(size, arb.capacity());
        for (int i = 0; i < size; i++) {
            assertEquals(i, arb.fillCount());
            arb.enqueue("aaa");
        }
        assertEquals(size, arb.fillCount());
        for (int i = 0; i < size; i++) {
            assertEquals(size - i, arb.fillCount());
            arb.dequeue();
        }
        assertEquals(0, arb.fillCount());
    }

    @Test
    public void iteratorTest() {
        int size = 100;
        int base = 3778;

        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<Integer>(size);
        assertEquals(size, arb.capacity());
        for (int i = 0; i < size; i++) {
            arb.enqueue(base);
        }
        for (int x: arb) {
            assertEquals(x, base);
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
}
