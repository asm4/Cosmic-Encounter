package org.cosmicencounter.exceptions;

/**
 * Base Exception that all exceptions will extend
 */
public class CosmicException extends Exception {
    public CosmicException() {
        super();
    }

    public CosmicException(String message) {
        super(message);
    }

    public CosmicException(Throwable e) {
        super(e);
    }

    public CosmicException(String message, Throwable e) {
        super(message, e);
    }
}
