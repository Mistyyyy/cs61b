public class LinkedListDeque<T> {
    /* represent the deque's length */
    private int size;
    /* the pointer which is point to the first Node in LinkedListDeque */
    private Node<T> first;
    /* the pointer which is point to the last Node in LinkedListDeque  */
    private Node<T> last;

    public class Node<T> {
        private T item;
        private Node<T> next;
        private Node<T> previous;
        public Node(T value) {
            item = value;
            next = null;
            previous = null;
        }
    }

    public LinkedListDeque() {
        size = 0;
        first = null;
        last = null;
    }

    private void add(T item) {
        size += 1;
        Node<T> newNode = new Node(item);
        if (first == null) {
            first = newNode;
            last = first;
        } else {
            last.next = newNode;
            newNode.previous = last;
            last = newNode;
        }
    }

    private void remove() {
        if (last != null) {
            size -= 1;
            if (first == last) {
                first = last = null;
            } else {
                Node<T> temNode = last.previous;
                last.previous = null;
                temNode.next = null;
                last = temNode;
            }
        }
    }

    public void addFirst(T item) {
        size += 1;
        Node<T> newNode = new Node(item);
        if (first == null) {
            first = newNode;
            last = first;
        } else {
            newNode.next = first;
            first.previous = newNode;
            first = newNode;
        }
    }

    public void addLast(T item) {
        this.add(item);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node<T> current = first;
        while (current != null) {
            System.out.print(current.item + " ");
            current = current.next;
        }
    }

    public T removeFirst() {
        if (first == null) {
            return null;
        }
        size -= 1;
        T res = first.item;
        if (first == last) {
            first = last = null;
        } else {
            Node<T> tempNode = first.next;
            first.next = null;
            tempNode.previous = null;
            first = tempNode;
        }
        return res;
    }

    public T removeLast() {
        if (last == null) {
            return null;
        }
        size -= 1;
        T res = last.item;
        if (first == last) {
            first  = last = null;
        } else {
            Node<T> tempNode = last.previous;
            last.previous = null;
            tempNode.next = null;
            last = tempNode;
        }
        return res;
    }

    public T get(int index) {
        if (index > size) {
            return null;
        }
        int i = 0;
        Node<T> current = first;
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
        if (node == null) {
            return null;
        }
        return getItemRecursive(index--, node.next);
    }

    /*
    * @param index: which element should be return
    * */
    public T getRecursive(int index) {
        return getItemRecursive(index, first);
    }
}
