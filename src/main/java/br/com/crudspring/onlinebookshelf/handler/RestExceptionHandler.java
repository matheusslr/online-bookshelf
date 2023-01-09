package br.com.crudspring.onlinebookshelf.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.crudspring.onlinebookshelf.exceptions.BadRequestExceptionDetails;
import br.com.crudspring.onlinebookshelf.exceptions.BadRequestExeception;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BadRequestExeception.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestExeception badRequestExeception){
        return new ResponseEntity<BadRequestExceptionDetails>(
            BadRequestExceptionDetails.builder()
            .title("Bad Request, check documentation")
            .details(badRequestExeception.getMessage())
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .build(), HttpStatus.BAD_REQUEST);
    }
}
