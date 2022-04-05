package com.example.bzoor_second.handler;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String message, Exception ex, HttpStatus forbidden, ZonedDateTime z) {
        super(message);
    }
    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }
}