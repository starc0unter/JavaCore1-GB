package ru.chentsov.javacore;

/**
 * @author Chentsov Evgenii
 */
public class TestArray implements Runnable {

    private final float[] array;
    private final int startIndex;

    public float[] getArray() {
        return array;
    }

    /**
     * Constructor for TestArray class.
     * @param array a float array to apply compute() to
     * @param startIndex is required for parallel computations
     *                   because index of the original array is
     *                   explicitly used in compute()
     */
    public TestArray(final float[] array, final int startIndex) {
        this.array = array;
        this.startIndex = startIndex;
    }

    public synchronized void compute() {
        for (int i = 0; i < array.length; i++) {
            final int correctedIndex = i + startIndex;
            array[i] = (float)(array[i] * Math.sin(0.2f + correctedIndex / 5) *
                    Math.cos(0.2f + correctedIndex / 5) * Math.cos(0.4f + correctedIndex / 2));
        }
    }

    @Override
    public void run() {
        compute();
    }

}
