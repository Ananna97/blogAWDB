package com.example.blog.dto;


import com.example.blog.model.Comment;
import com.example.blog.model.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDetailsDTO {
    private Long id;
    private String title;
    private String body;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String authorFirstName;
    private String authorLastName;
    private List<Rating> ratings;
    private List<Comment> comments;

}