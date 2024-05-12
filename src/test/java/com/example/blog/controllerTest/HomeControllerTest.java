package com.example.blog.controllerTest;

import com.example.blog.controller.HomeController;
import com.example.blog.model.Post;
import com.example.blog.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HomeControllerTest {

    @Mock
    private PostService postService;

    @Mock
    private Model model;

    @InjectMocks
    private HomeController homeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testHome() {
        // Arrange
        List<Post> posts = new ArrayList<>();
        posts.add(new Post());
        when(postService.findAll()).thenReturn(posts);

        // Act
        String viewName = homeController.home(model);

        // Assert
        assertEquals("home", viewName);
        verify(postService, times(1)).findAll();
        verify(model, times(1)).addAttribute("posts", posts);
    }
}
