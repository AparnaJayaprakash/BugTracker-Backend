package com.bugtracker.exception;

public class BugNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;

	public BugNotFoundException() {
        super();
    }

    public BugNotFoundException(String message) {
        super(message);
    }

    public BugNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BugNotFoundException(Throwable cause) {
        super(cause);
    }

    public BugNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
