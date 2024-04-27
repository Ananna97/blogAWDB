package com.example.blog.exceptions;

public class RatingServiceException extends RuntimeException {
    public RatingServiceException(String message) {
        super(message);
    }
}