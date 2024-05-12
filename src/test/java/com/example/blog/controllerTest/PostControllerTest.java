package com.example.blog.controllerTest;

import com.example.blog.controller.PostController;
import com.example.blog.model.Post;
import com.example.blog.model.User;
import com.example.blog.service.CategoryService;
import com.example.blog.service.CommentService;
import com.example.blog.service.PostService;
import com.example.blog.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PostControllerTest {

    @Mock
    private PostService postService;

    @Mock
    private UserService userService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private CommentService commentService;

    @Mock
    private Model model;

    @Mock
    private Principal principal;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateNewPost() {
        // Arrange
        Post post = new Post();
        when(userService.findByEmail(any())).thenReturn(Optional.of(new User()));

        // Act
        String viewName = postController.createNewPost(post, principal);

        // Assert
        assertEquals("redirect:/", viewName);
        verify(postService, times(1)).save(post);
    }

    @Test
    void testGetPostForEdit() {
        // Arrange
        Long postId = 1L;
        Post post = new Post();
        when(postService.findById(postId)).thenReturn(post);

        // Act
        String viewName = postController.getPostForEdit(postId, model);

        // Assert
        assertEquals("post_edit", viewName);
        verify(postService, times(1)).findById(postId);
        verify(model, times(1)).addAttribute("post", post);
    }

    @Test
    void testUpdatePost() {
        // Arrange
        Long postId = 1L;
        Post existingPost = new Post();
        existingPost.setId(postId);
        existingPost.setTitle("Old Title");
        existingPost.setBody("Old Body");

        Post updatedPost = new Post();
        updatedPost.setId(postId);
        updatedPost.setTitle("New Title");
        updatedPost.setBody("New Body");

        when(postService.findById(postId)).thenReturn(existingPost);

        // Act
        String viewName = postController.updatePost(postId, updatedPost);

        // Assert
        assertEquals("redirect:/posts/" + postId, viewName);
        assertEquals("New Title", existingPost.getTitle());
        assertEquals("New Body", existingPost.getBody());
        verify(postService, times(1)).findById(postId);
        verify(postService, times(1)).save(existingPost);
    }

    @Test
    void testDeletePost() {
        // Arrange
        Long postId = 1L;
        Post post = new Post();
        when(postService.findById(postId)).thenReturn(post);

        // Act
        String viewName = postController.deletePost(postId);

        // Assert
        assertEquals("redirect:/", viewName);
        verify(postService, times(1)).findById(postId);
        verify(postService, times(1)).delete(post);
    }
}
