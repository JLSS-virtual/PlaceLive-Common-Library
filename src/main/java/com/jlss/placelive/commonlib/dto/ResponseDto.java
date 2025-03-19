package com.jlss.placelive.commonlib.dto;

import lombok.Getter;
import lombok.Setter;
/**
 * Generic response wrapper for API responses.
 *
 * @param <T> the type of the response data
 */
@Getter
@Setter
public class ResponseDto<T> {

    /**
     * Indicates whether the request was successful.
     */
    private Boolean success = true;

    /**
     * The response data, if the request was successful.
     */
    private T data;

    /**
     * The error code, if the request failed.
     */
    private String errorCode;

    /**
     * The error message, if the request failed.
     */
    private String errorMessage;

    public ResponseDto(Boolean success, T data, String errorCode, String errorMessage) {
        this.success = success;
        this.data = data;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}