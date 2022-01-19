package com.jumia.task.exceptions;

public class JPayBusinessException extends RuntimeException{
    String message;
    public JPayBusinessException(String message){
        super();
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
