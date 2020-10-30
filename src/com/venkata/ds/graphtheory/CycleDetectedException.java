package com.venkata.ds.graphtheory;

public class CycleDetectedException  extends Exception{

    public CycleDetectedException() {
        super();
    }

    public CycleDetectedException(String message) {
        super(message);
    }

    public CycleDetectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CycleDetectedException(Throwable cause) {
        super(cause);
    }

    protected CycleDetectedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
