package ru.chentsov.javacore;

public final class App
{

    private static final int SIZE = 10_000_000;
    private static final int H = SIZE / 2 ;
    private static final float[] ARRAY = new float[SIZE];

    public static void main(String[] args)
    {
        System.out.println("Computing  using sequential version...");
        final long sequentialTime = computeSequential();
        System.out.println("...Done. It took " + sequentialTime + " milliseconds");

        System.out.println("\nComputing using parallel version...");
        final long concurrentTime = computeConcurrent();
        System.out.println("...Done. It took " + concurrentTime + " milliseconds");
    }

    private static long computeSequential() {
        fillArray(ARRAY);
        final long startTime = System.currentTimeMillis();
        final TestArray wholeArray =  new TestArray(ARRAY, 0);
        wholeArray.compute();
        System.out.println(getCheckSum(wholeArray.getArray()));
        final long endTime = System.currentTimeMillis();

        return endTime - startTime;
    }

    private static long computeConcurrent() {
        fillArray(ARRAY);
        final float[] firstPart = new float[H];
        final float[] secondPart = new float[SIZE - H];

        final long startTime = System.currentTimeMillis();
        System.arraycopy(ARRAY, 0, firstPart, 0, H);
        System.arraycopy(ARRAY, H, secondPart, 0, SIZE - H);

        final TestArray first = new TestArray(firstPart, 0);
        final Thread firstPartThread = new Thread(first);
        final TestArray second = new TestArray(secondPart, H);
        final Thread secondPartThread = new Thread(second);

        firstPartThread.start();
        secondPartThread.start();

        try {
            firstPartThread.join();
            secondPartThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(first.getArray(), 0, ARRAY, 0, H);
        System.arraycopy(second.getArray(), 0, ARRAY, H, SIZE - H);

        System.out.println(getCheckSum(ARRAY));
        final long endTime = System.currentTimeMillis();

        return endTime - startTime;
    }

    private static void fillArray(final float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) 1;
        }
    }

    /**
     * Performs a simple sum check by summarizing array elements
     * @param arr an array to check
     * @return String containing the sum
     */
    private static String getCheckSum(final float[] arr) {
        float sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        return "Checksum is " + sum;
    }

}
