package ru.chentsov.javacore.exceptions;

/**
 * @author Chentsov Evgenii
 */
public class MyArrayDataException extends Exception {

    private int rows;
    private int columns;

    public MyArrayDataException(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public String getMessage() {
        return "There is a problem with the data type in position [" + columns + "][" + rows + "].";
    }

}
