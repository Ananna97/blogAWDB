package com.example.blog.service;

import com.example.blog.exceptions.CommentServiceException;
import com.example.blog.model.Comment;
import com.example.blog.model.Post;
import com.example.blog.repository.CommentRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;




@Slf4j
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public List<Comment> findAllByPost(Post post) {
        return commentRepository.findByPost(post);
    }

    public Comment save(@Valid Comment comment) {
        if (comment.getPost() == null) {
            throw new CommentServiceException("Post is required for saving a comment");
        }
        return commentRepository.save(comment);
    }
}