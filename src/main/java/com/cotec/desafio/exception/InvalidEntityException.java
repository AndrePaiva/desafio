package com.cotec.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidEntityException extends RuntimeException {

    public InvalidEntityException() {
    }

    public InvalidEntityException(String message) {
        super(message);
    }

}
