package com.assembleia.votacao.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;

public class ObjectNotFoundException extends RuntimeException{


    public ObjectNotFoundException(String message){
        super(message);
    }
}
