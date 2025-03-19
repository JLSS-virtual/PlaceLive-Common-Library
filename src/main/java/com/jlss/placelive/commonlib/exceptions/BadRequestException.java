package com.jlss.placelive.commonlib.exceptions;
import lombok.Getter;
import lombok.Setter;

/**
 * Exception representing a bad request due to invalid business logic.
 */
@Getter
@Setter
public class BadRequestException extends RuntimeException {

    private final String errorCode;

    /**
     * Constructs a new BadRequestException with the given message.
     *
     * @param message the error message
     */
    public BadRequestException(String message) {
        super(message);
        this.errorCode = null;  // Optional: No error code passed
    }

    /**
     * Constructs a new BadRequestException with the given message and error code.
     *
     * @param errorCode the error code associated with the exception
     * @param message   the error message
     */
    public BadRequestException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
