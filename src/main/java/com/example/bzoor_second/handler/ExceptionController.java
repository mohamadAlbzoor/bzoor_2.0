package com.example.bzoor_second.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = MyException.class)
    public ResponseEntity<Object> handelYaHoop(HttpServletRequest req, Exception ex){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorsShape deleteExceptionShape = new ErrorsShape(ex.getMessage(),ex, badRequest, ZonedDateTime.now(ZoneId.of("Z")));
        ResponseEntity<Object> re = new ResponseEntity<>(deleteExceptionShape, badRequest);
        return re;
    }

    @ExceptionHandler(value = IncorrectCredentials.class)
    public ResponseEntity<Object> incorrectCredentials(HttpServletRequest req, Exception ex){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorsShape incorrectCredentialsShape = new ErrorsShape(ex.getMessage(),ex, badRequest, ZonedDateTime.now(ZoneId.of("Z")));
        ResponseEntity<Object> re = new ResponseEntity<>(incorrectCredentialsShape, badRequest);
        return re;
    }

    @ExceptionHandler(value = NotAuthorized.class)
    public ResponseEntity<Object> NotAuthorized(HttpServletRequest req, Exception ex){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorsShape incorrectCredentialsShape = new ErrorsShape(ex.getMessage(),ex, badRequest, ZonedDateTime.now(ZoneId.of("Z")));
        ResponseEntity<Object> re = new ResponseEntity<>(incorrectCredentialsShape, badRequest);
        return re;
    }

    @ExceptionHandler(value = ForbiddenException.class)
    public ResponseEntity<Object> notAuthenticated(HttpServletRequest req, Exception ex){
        System.out.println("here yboooyyy");
        HttpStatus forbidden = HttpStatus.FORBIDDEN;
        ErrorsShape forbiddenException = new ErrorsShape(ex.getMessage(),ex, forbidden, ZonedDateTime.now(ZoneId.of("Z")));
        ResponseEntity<Object> re = new ResponseEntity<>(forbiddenException, forbidden);
        return re;
    }

}