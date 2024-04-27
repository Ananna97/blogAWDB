package com.example.blog.exceptions;

public class CommentServiceException extends RuntimeException {
    public CommentServiceException(String message) {
        super(message);
    }
}
