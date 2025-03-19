package com.jlss.placelive.commonlib.exceptions.handler;

import com.jlss.placelive.commonlib.dto.ResponseDto;
import com.jlss.placelive.commonlib.enums.ErrorCode;
import com.jlss.placelive.commonlib.exceptions.BadRequestException;
import com.jlss.placelive.commonlib.exceptions.NotFoundException;
import com.jlss.placelive.commonlib.exceptions.ResourceAlreadyExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;
@Component
@RestControllerAdvice
public class GlobalExceptionHandler  {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handle validation exceptions caused by invalid input.
     *
     * @param ex the MethodArgumentNotValidException containing validation errors
     * @return a response with error details
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        logger.info("MethodArgumentNotValidException : cought");
        String errorMessage = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        ResponseDto<String> response = new ResponseDto<>(false, null, ErrorCode.BAD0001.getCode(), errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Handle bad request exceptions caused by invalid business logic.
     *
     * @param ex the BadRequestException
     * @return a response with error details
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseDto<String>> handleBadRequestException(BadRequestException ex) {
        logger.info("BadRequestException : cought");
        // Get error code and message from the exception or use default
        String errorCode = ex.getErrorCode() != null ? ex.getErrorCode() : ErrorCode.BAD0001.getCode();
        String message = ex.getMessage() != null ? ex.getMessage() : ErrorCode.BAD0001.getMessage();
        ResponseDto<String> response = new ResponseDto<>(false, null, errorCode, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Handle resource already exists exceptions caused by attempts to create a resource that already exists.
     *
     * @param ex the ResourceAlreadyExistException
     * @return a response with error details
     */
    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ResponseDto<String>> handleResourceAlreadyExistException(ResourceAlreadyExistException ex) {
        logger.info("ResourceAlreadyExistException : cought");
        // Get error code and message from the exception or use default
        String errorCode = ex.getErrorCode() != null ? ex.getErrorCode() : ErrorCode.RES0001.getCode();
        String message = ex.getMessage() != null ? ex.getMessage() : ErrorCode.RES0001.getMessage();
        ResponseDto<String> response = new ResponseDto<>(false, null, errorCode, message);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);  // Conflict for resource already exists
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseDto<String>> handleNotFoundException(NotFoundException ex) {
        logger.info("NotFoundException : cought");
        // Get error code and message from the exception or use default
        String errorCode = ex.getErrorCode() != null ? ex.getErrorCode() : ErrorCode.ERR404.getCode();
        String message = ex.getMessage() != null ? ex.getMessage() : ErrorCode.ERR404.getMessage();
        ResponseDto<String> response = new ResponseDto<>(false, null, errorCode, message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /**
     * Handle generic exceptions that are not explicitly handled elsewhere.
     *
     * @param ex the Exception
     * @return a response with a generic error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<String>> handleGenericException(Exception ex) {
        ResponseDto<String> response = new ResponseDto<>(false, null, ErrorCode.GEN0001.getCode(), ErrorCode.GEN0001.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}