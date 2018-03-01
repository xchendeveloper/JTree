package com.suppercoder.java.jtree;

/**
 * data type exception
 *
 * @author chenxing
 * @create 2018-02-01 12:14
 **/

public class DataTypeException extends RuntimeException {

    public DataTypeException() {
    }

    public DataTypeException(String message) {
        super(message);
    }

    public DataTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataTypeException(Throwable cause) {
        super(cause);
    }
}
