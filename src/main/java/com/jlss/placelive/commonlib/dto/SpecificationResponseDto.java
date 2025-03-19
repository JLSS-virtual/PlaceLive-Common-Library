package com.jlss.placelive.commonlib.dto;

import jakarta.persistence.criteria.Join;
import lombok.Getter;
import lombok.Setter;

import java.util.jar.JarOutputStream;

/**
 * Generic response for creating the query.
 *
 */
@Getter
@Setter
public class SpecificationResponseDto {

    /**
     * Indicates whether the request was successful.
     */
    private Boolean success = true;

    private String field;

    private String fieldName;

    private Boolean isJoinPresent;
    private Join<?,?> join;


    /**
     * The error code, if the request failed.
     */
    private String errorCode;

    /**
     * The error message, if the request failed.
     */
    private String errorMessage;

    public SpecificationResponseDto(Boolean success, String field, String fieldName, Boolean isJoinPresent, String errorCode, String errorMessage, Join<?,?> join) {
        this.success = success;
        this.field = field;
        this.fieldName = fieldName;
        this.isJoinPresent = isJoinPresent;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.join = join;
    }

    public SpecificationResponseDto() {

    }
}
