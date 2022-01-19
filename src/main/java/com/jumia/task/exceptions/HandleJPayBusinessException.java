package com.jumia.task.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleJPayBusinessException {
    @ExceptionHandler(JPayBusinessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleNoRecordFoundException(JPayBusinessException ex)
    {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("country code : " + ex.getMessage() +" doesn't exist in lookup table");
        return errorResponse;
    }
}
