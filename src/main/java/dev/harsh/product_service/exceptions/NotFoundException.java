package dev.harsh.product_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
