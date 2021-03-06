public class ArrayDequeTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    public static boolean checkEqual(int expected, int actual) {
        if (expected != actual) {
            System.out.println("get() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");
        ArrayDeque<String> lld1 = new ArrayDeque<String>();

        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, lld1.size()) && passed;
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.addLast("middle");
        passed = checkSize(2, lld1.size()) && passed;

        lld1.addLast("back");
        passed = checkSize(3, lld1.size()) && passed;

        System.out.println("Printing out deque: ");
        lld1.printDeque();

        printTestStatus(passed);
    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public static void addRemoveTest() {
        System.out.println("Running add/remove test.");
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst(10);
        int val = lld1.removeLast();

        passed = checkEmpty(true, lld1.isEmpty()) && passed;

        passed = checkEqual(10, val) && passed;

        lld1.addFirst(11);
        int val1 = lld1.removeFirst();

        passed = checkEmpty(true, lld1.isEmpty()) && passed;
        passed = checkEqual(11,  val1) && passed;

        printTestStatus(passed);
    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public static void addRemoveResizeTest() {

        System.out.println("Running add/remove resizing test.");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        int removed = lld1.removeFirst();

        passed = checkEqual(10, removed) && passed;

        lld1.addLast(11);
        lld1.addLast(12);
        lld1.addLast(13);
        lld1.addFirst(14);
        lld1.addFirst(15);
        lld1.addFirst(16);
        lld1.addLast(17);
        lld1.addLast(18);
        lld1.addLast(19);
        //should not be empty
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        int removed1 = lld1.removeLast();
        passed = checkEqual(19, removed1) && passed;

        // should be empty
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        printTestStatus(passed);
    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public static void getItemTest() {

        int N = 50;

        System.out.println("Running getItem test.");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        boolean passed = checkEmpty(true, lld1.isEmpty());

        for (int i = 0; i < N; i++) {
            lld1.addLast(i);
        }

        for (int i = N; i > 0; i--) {
            // should be empty
            passed = checkEqual(N - i, lld1.get(N - i)) && passed;
        }

        printTestStatus(passed);
    }

    public static void getRemoveRandomTest() {
        int N = 500;
        int factor = 1979;

        System.out.println("Running getRemoveRandom Test");
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        // should be equal
        boolean passed = checkEmpty(true, lld1.isEmpty());

        for (int i = 0; i < N; i++) {
            lld1.addLast(i + factor);
        }

        for (int i = N - 1; i >= 0; i--) {
            passed = checkEqual(i + factor, lld1.removeLast()) && passed;
        }

        passed = checkEmpty(true, lld1.isEmpty());
        printTestStatus(passed);
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addRemoveResizeTest();
        getRemoveRandomTest();
        addRemoveTest();
        getItemTest();
    }
}
