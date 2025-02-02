package io.realyusufismail.elastic.api;


import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Thrown by {@link CredentialsVerifier#verify(ObjectNode)} when authentication credentials are
 * invalid.
 */
public class InvalidCredentialsException extends Exception {

    /**
     * Creates a new InvalidCredentialsException with a null detail message.
     */
    public InvalidCredentialsException() {}

    /**
     * Creates a new InvalidCredentialsException with the specified message.
     *
     * @param message message the exception detail message
     */
    public InvalidCredentialsException(String message) {
        super(message);
    }

    /**
     * Creates a new InvalidCredentialsException with the specified detail message and cause.
     *
     * @param message the exception detail message
     * @param throwable the Throwable that caused this exception
     */
    public InvalidCredentialsException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
