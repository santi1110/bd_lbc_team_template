package com.amazon.ata.music.playlist.service.exceptions;

/**
 * Exception to throw when an attribute change is invalid.
 */
public class InvalidAttributeChangeException extends AttributeException {

    private static final long serialVersionUID = 8765432109L;

    public InvalidAttributeChangeException() {
        super();
    }

    public InvalidAttributeChangeException(String message) {
        super(message);
    }

    public InvalidAttributeChangeException(Throwable cause) {
        super(cause);
    }

    public InvalidAttributeChangeException(String message, Throwable cause) {
        super(message, cause);
    }
}

