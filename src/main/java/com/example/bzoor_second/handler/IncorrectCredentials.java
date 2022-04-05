package com.example.bzoor_second.handler;

public class IncorrectCredentials extends RuntimeException{
    public IncorrectCredentials(String message) {
        super(message);
    }

    public IncorrectCredentials(String message, Throwable cause) {
        super(message, cause);
    }
}