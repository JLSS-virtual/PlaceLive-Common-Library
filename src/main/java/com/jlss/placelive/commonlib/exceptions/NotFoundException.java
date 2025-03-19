package com.jlss.placelive.commonlib.exceptions;


import lombok.Getter;
import lombok.Setter;

/**
 * Exception representing not found response.
 */
@Getter
@Setter
public class NotFoundException  extends RuntimeException {

  private String errorCode;

  /**
   * Construct a new NotFoundException with the given message.
   *
   * @param message the error message
   */

  public NotFoundException(String message){
    super(message);
  }
  /**
   * Construct a new NotFoundException with the given message and error code.
   *
   * @param errorCode the error code for more context
   * @param message   the error message
   */
  public NotFoundException(String errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }
}