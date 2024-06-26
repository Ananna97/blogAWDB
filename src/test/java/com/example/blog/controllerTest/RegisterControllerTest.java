package com.example.blog.controllerTest;

import com.example.blog.controller.RegisterController;
import com.example.blog.dto.RegisterDTO;
import com.example.blog.model.User;
import com.example.blog.service.UserService;
import com.example.blog.config.PasswordValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RegisterControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private PasswordValidator passwordValidator;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private RegisterController registerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getRegisterForm() {
        RegisterDTO registerDTO = new RegisterDTO();

        String viewName = registerController.getRegisterForm(model);

        assertEquals("register", viewName);
        verify(model).addAttribute(eq("registerDTO"), any(RegisterDTO.class));
    }

    @Test
    void registerNewUser_Success() {
        RegisterDTO registerDTO = new RegisterDTO();
        User user = new User();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.save(any(User.class))).thenReturn(user);
        String viewName = registerController.registerNewUser(registerDTO, bindingResult);

        assertEquals("redirect:/", viewName);
        verify(userService).save(any(User.class));
    }

    @Test
    void registerNewUser_Failure() {
        RegisterDTO registerDTO = new RegisterDTO();

        when(bindingResult.hasErrors()).thenReturn(true);
        String viewName = registerController.registerNewUser(registerDTO, bindingResult);

        assertEquals("register", viewName);
    }
}
