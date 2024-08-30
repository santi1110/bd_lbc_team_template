package com.amazon.ata.music.playlist.service.exceptions;

/**
 * Generic exception for attribute-related issues.
 */
public class AttributeException extends RuntimeException {

    private static final long serialVersionUID = 1234567890L;

    public AttributeException() {
        super();
    }

    public AttributeException(String message) {
        super(message);
    }

    public AttributeException(Throwable cause) {
        super(cause);
    }

    public AttributeException(String message, Throwable cause) {
        super(message, cause);
    }
}
