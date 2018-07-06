package ru.chentsov.javacore;

import ru.chentsov.javacore.exceptions.MyArrayDataException;
import ru.chentsov.javacore.exceptions.MyArraySizeException;

/**
 * @author Chentsov Evgenii
 */
public class App
{

    public static void main(String[] args) {
        //generating array that has a wrong length so MyArraySizeException is thrown
        tryGetSum(new String[4][5]);

        //setting correct array length and assigning values
        String[][] a = new String[4][4];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = String.valueOf(i + j + 1);
            }
        }
        tryGetSum(a);

        // setting wrong value so MyArrayDataException is thrown
        a[2][3] = "hello";
        tryGetSum(a);
    }

    private static void tryGetSum(String[][] a) {
        try {
            System.out.println(getSum(a));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int getSum(String[][] a) throws MyArraySizeException, MyArrayDataException {
        int requiredSize = 4;
        if (a.length != requiredSize || a[0].length != requiredSize) {
            throw new MyArraySizeException();
        }

        int sum = 0;
        for (int i = 0; i < requiredSize; i++) {
            for (int j = 0; j < requiredSize; j++) {
                try {
                    sum += Integer.parseInt(a[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }

        return sum;
    }

}
