package ru.chentsov.javacore.exceptions;

/**
 * @author Chentsov Evgenii
 */
public class MyArraySizeException extends Exception {

    @Override
    public String getMessage() {
        return "Wrong array size! it must be 4x4.";
    }

}
