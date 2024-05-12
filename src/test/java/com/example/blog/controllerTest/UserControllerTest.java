package com.example.blog.controllerTest;

import com.example.blog.controller.UserController;
import com.example.blog.model.User;
import com.example.blog.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers() {
        User user1 = new User();
        user1.setEmail("test1@example.com");
        user1.setPassword("password1");

        User user2 = new User();
        user2.setEmail("test2@example.com");
        user2.setPassword("password2");

        List<User> expectedUsers = Arrays.asList(user1, user2);
        when(userService.findAll()).thenReturn(expectedUsers);

        List<User> result = userController.getAllUsers();

        assertEquals(expectedUsers.size(), result.size());
        assertEquals(expectedUsers.get(0).getEmail(), result.get(0).getEmail());
        assertEquals(expectedUsers.get(1).getEmail(), result.get(1).getEmail());
        verify(userService, times(1)).findAll();
    }

    @Test
    void addUser() {
        User newUser = new User();
        newUser.setEmail("test@example.com");
        newUser.setPassword("password");

        when(userService.save(any(User.class))).thenReturn(newUser);

        User result = userController.addUser(newUser);

        assertEquals(newUser.getEmail(), result.getEmail());
        assertEquals(newUser.getPassword(), result.getPassword());
        verify(userService, times(1)).save(any(User.class));
    }
}
