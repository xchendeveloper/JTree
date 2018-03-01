package com.suppercoder.java.jtree;

/**
 * tree init exception
 *
 * @author chenxing
 * @create 2018-03-01 11:57
 **/

public class TreeInitException extends RuntimeException {
    public TreeInitException() {
    }

    public TreeInitException(String message) {
        super(message);
    }

    public TreeInitException(String message, Throwable cause) {
        super(message, cause);
    }

    public TreeInitException(Throwable cause) {
        super(cause);
    }
}
