package org.cosmicencounter.exceptions;

/**
 * Exception when the player attempts to preform an invalid move
 */
public class InvalidMoveException extends CosmicException {
    public InvalidMoveException() {
        super();
    }

    public InvalidMoveException(String message) {
        super(message);
    }

    public InvalidMoveException(Throwable e) {
        super(e);
    }

    public InvalidMoveException(String message, Throwable e) {
        super(message, e);
    }
}
