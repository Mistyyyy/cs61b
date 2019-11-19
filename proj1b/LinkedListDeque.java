public class LinkedListDeque<T> implements Deque<T> {
    /* represent the deque's length */
    private int size;
    /* the pointer which is point to the first Node in LinkedListDeque */
    private Node<T> sentinel;
    /* the pointer which is point to the last Node in LinkedListDeque  */
    private Node<T> lastSentinel;

    private class Node<T> {
        private T item;
        private Node<T> next;
        private Node<T> previous;
        Node(T value) {
            item = value;
            next = null;
            previous = null;
        }
    }

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null);
        lastSentinel = new Node(null);
        sentinel.next = lastSentinel;
        lastSentinel.previous = sentinel;
    }

    private void add(T item) {
        Node<T> newNode = new Node(item);
        newNode.previous = lastSentinel.previous;
        newNode.next = lastSentinel;
        lastSentinel.previous.next = newNode;
        lastSentinel.previous = newNode;
        size += 1;
    }

    private void remove() {
        if (lastSentinel.previous.item != null) {
            Node<T> tempNode = lastSentinel.previous;
            lastSentinel.previous = tempNode.previous;
            tempNode.previous.next = lastSentinel;
            tempNode.previous = null;
            tempNode.next = null;
            size -= 1;
        }
    }

    @Override
    public void addFirst(T item) {
        Node<T> newNode = new Node(item);
        Node<T> tempNode = sentinel.next;
        sentinel.next = newNode;
        tempNode.previous = newNode;
        newNode.previous = sentinel;
        newNode.next = tempNode;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        this.add(item);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node<T> current = sentinel.next;
        while (current != null) {
            System.out.print(current.item + " ");
            current = current.next;
        }
    }

    @Override
    public T removeFirst() {
        if (sentinel.next.item != null) {
            Node<T> willRemoved = sentinel.next;
            sentinel.next = willRemoved.next;
            willRemoved.next.previous = sentinel;
            willRemoved.next = null;
            willRemoved.previous = null;
            size -= 1;
            return willRemoved.item;
        }
        return null;
    }

    @Override
    public T removeLast() {
        if (lastSentinel.previous.item != null) {
            Node<T> willRemoved = lastSentinel.previous;
            remove();
            return willRemoved.item;
        } else {
            return null;
        }
    }

    @Override
    public T get(int index) {
        if (index > size) {
            return null;
        }
        int i = 0;
        Node<T> current = sentinel.next;
        while (i < index) {
            current = current.next;
            i += 1;
        }
        return current.item;
    }

    private T getItemRecursive(int index, Node<T> node) {
        if (index == 0) {
            return node.item;
        }
        if (node == null || index < 0) {
            return null;
        }
        return getItemRecursive(--index, node.next);
    }

    /*
    * @param index: which element should be return
    * */
    public T getRecursive(int index) {
        return getItemRecursive(index, sentinel.next);
    }
}
