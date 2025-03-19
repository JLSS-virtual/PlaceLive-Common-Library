package com.jlss.placelive.commonlib.enums;

import lombok.Getter;

/**
 * Enum representing various error codes, messages, and HTTP statuses
 * used for error handling across the application.
 * <p>
 * This enum is structured in categories such as:
 * <ul>
 *     <li>Generic Errors (GEN)</li>
 *     <li>Bad Request Errors (BAD)</li>
 *     <li>Resource Already Exists Errors (RES)</li>
 * </ul>
 * </p>
 * Each error code contains a unique identifier (code), an error message,
 * and an associated HTTP status.
 */
@Getter
public enum ErrorCode {

    /**
     * Generic error when an unexpected error occurs in the application.
     */
    GEN0001("GEN0001", "An unexpected error occurred", 500),

    /**
     * Bad Request error for generic bad requests.
     */
    BAD0001("BAD0001", "Bad Request", 400),
    /**
     * Not Found error for generic empty results.
     */
    ERR404("ERR404", "Not Found", 404),
    /**
     * Internal server error.
     */
    ERR500("ERR500", "Server error", 500),

    /**
     * Error indicating that a resource already exists.
     */
    RES0001("RES0001", "Resource already exists", 409);

    /**
     * The unique code representing the error.
     */
    private final String code;

    /**
     * The message associated with the error.
     */
    private final String message;

    /**
     * The HTTP status associated with the error.
     */
    private final int status;

    /**
     * Constructs an instance of the ErrorCode enum with the specified values.
     *
     * @param code the unique error code.
     * @param message the message associated with the error.
     * @param status the HTTP status code associated with the error.
     */
    ErrorCode(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}