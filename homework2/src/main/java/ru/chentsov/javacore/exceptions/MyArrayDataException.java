package ru.chentsov.javacore.exceptions;

/**
 * @author Chentsov Evgenii
 */
public class MyArrayDataException extends Exception {

    private int i;
    private int j;

    public MyArrayDataException(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public String getMessage() {
        return "There is a problem with the data type in position [" + i + "][" + j + "].";
    }

}
