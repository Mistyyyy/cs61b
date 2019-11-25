package synthesizer;

public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    /* Sampling Rate */
    private static final int SR = 44100;
    /* energy decay factor */
    private static final double DECAY = .996;

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        buffer = new ArrayRingBuffer<Double>((int) Math.round(SR / frequency));
    }

    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }
        while (!buffer.isFull()) {
            double r = Math.random() - 0.5;
            buffer.enqueue(r);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        if (!buffer.isEmpty()) {
            double first = buffer.dequeue();
            double second = sample();
            double res = DECAY * (first + second) / 2;
            buffer.enqueue(res);
        }
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        if (!buffer.isEmpty()) {
            return buffer.peek();
        }
        return 0.9;
    }
}
