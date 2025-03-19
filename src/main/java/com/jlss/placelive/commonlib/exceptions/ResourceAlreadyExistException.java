package com.jlss.placelive.commonlib.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * Exception representing a bad request due to an already existing resource.
 */
@Getter
@Setter
public class ResourceAlreadyExistException extends RuntimeException {

  private String errorCode;

  /**
   * Construct a new ResourceAlreadyExistException with the given message.
   *
   * @param message the error message
   */
  public ResourceAlreadyExistException(String message) {
    super(message);
  }

  /**
   * Construct a new ResourceAlreadyExistException with the given message and error code.
   *
   * @param errorCode the error code for more context
   * @param message   the error message
   */
  public ResourceAlreadyExistException(String errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }
}