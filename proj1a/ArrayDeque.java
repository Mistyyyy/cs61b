public class ArrayDeque<T> {
    private int size = 0;
    private int initArraySize = 8;
    private double factor = 0.25;
    private int frontPointer = -1;
    private int lastPointer = -1;
    private T[] dequeContainer = (T[]) new Object[initArraySize];

    public ArrayDeque() {

    }

    private void expandArray() {
        int tempInitArraySize = (int) (initArraySize * factor + initArraySize);
        T [] copyContainer = (T[]) new Object[tempInitArraySize];
        for (int i = 0; i < initArraySize; i++) {
            copyContainer[i] = get(i);
        }
        frontPointer = 0;
        lastPointer = initArraySize - 1;
        initArraySize = tempInitArraySize;
        dequeContainer = copyContainer;
    }

    private int getIncIndex(int index) {
        return (index + 1) % initArraySize;
    }

    private int getDescIndex(int index) {
        return (index -  1 + initArraySize) % initArraySize;
    }

    private void resetIdx() {
        frontPointer = -1;
        lastPointer = -1;
    }

    private void add(T item) {
        size += 1;
        int addIdx = 0;
        if (size > initArraySize) {
            expandArray();
        }
        if (size == 1) {
            frontPointer = 0;
        }
        addIdx = getIncIndex(lastPointer);
        dequeContainer[addIdx] = item;
        lastPointer = addIdx;
    }

    private void remove() {
        size -= 1;
        int removeIdx = getDescIndex(lastPointer);
//        dequeContainer[lastPointer] = null;
        lastPointer = removeIdx;
        if (isEmpty()) {
            resetIdx();
        }
    }

    public void addFirst(T item) {
        size += 1;
        if (size > initArraySize) {
            expandArray();
        }
        if (size == 1) {
            lastPointer = 0;
            frontPointer = 0;
        } else {
            frontPointer = getDescIndex(frontPointer);
        }
        dequeContainer[frontPointer] = item;
    }

    public void addLast(T item) {
        add(item);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            T readValue = get(i);
            System.out.print(readValue + " ");
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T res = dequeContainer[frontPointer];
//        dequeContainer[frontPointer] = null;
        frontPointer = getIncIndex(frontPointer);
        if (isEmpty()) {
            resetIdx();
        }
        return res;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T res = dequeContainer[lastPointer];
        remove();
        return res;
    }

    public T get(int index) {
        return dequeContainer[(index + frontPointer) % initArraySize];
    }
}
