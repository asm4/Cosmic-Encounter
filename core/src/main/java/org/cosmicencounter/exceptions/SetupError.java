package org.cosmicencounter.exceptions;

/**
 * An error while setting up the game.
 */
public class SetupError extends Error {
    public SetupError() {
        super();
    }

    public SetupError(String message) {
        super(message);
    }

    public SetupError(Throwable e) {
        super(e);
    }

    public SetupError(String message, Throwable e) {
        super(message, e);
    }
}
