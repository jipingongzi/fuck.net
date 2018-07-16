package com.example.service.exception;

/**
 * 无效操作异常
 * @author wangh
 */
public class InvalidOperationException extends Exception {

    public InvalidOperationException(){
        super();
    }
    public InvalidOperationException(String message){
        super(message);
    }
    public InvalidOperationException(String message, Throwable throwable){
        super(message, throwable);
    }
}
