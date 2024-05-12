package com.example.blog.controllerTest;

import com.example.blog.controller.LoginController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginControllerTest {

    @Test
    void showLoginForm() {
        LoginController loginController = new LoginController();
        String viewName = loginController.showLoginForm();
        assertEquals("login", viewName);
    }
}
