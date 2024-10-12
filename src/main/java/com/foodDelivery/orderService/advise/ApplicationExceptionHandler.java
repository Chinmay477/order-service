package com.foodDelivery.orderService.advise;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.foodDelivery.orderService.exception.UserNotFoundException;
import com.foodDelivery.orderService.external.generic.ErrorDetails;


@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDetails handleInvalidArgument(MethodArgumentNotValidException exception){

        String errorMessage = exception.getBindingResult().getFieldErrors().stream()
                                        .findFirst()
                                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                        .orElse("Invalid Input");
                                        
        return new ErrorDetails(HttpStatus.BAD_REQUEST.value(), errorMessage);

    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorDetails handleNotReadable(HttpMessageNotReadableException exception){
        
        return new ErrorDetails(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
    

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorDetails handleUserNotFound(UserNotFoundException exception){
        
        return new ErrorDetails(HttpStatus.NOT_FOUND.value(), exception.getMessage());

    }
    
}
