package com.example.blog.exceptions;

public class PostServiceException extends RuntimeException {
    public PostServiceException(String message) {
        super(message);
    }
}