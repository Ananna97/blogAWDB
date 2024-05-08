package com.example.blog.controller;

import com.example.blog.dto.LoginDTO;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showLoginForm() {
        return "login";
    }
    @PostMapping
    public String login(LoginDTO loginDTO, RedirectAttributes redirectAttributes) {
        try {
            userService.authenticate(loginDTO.getEmail(), loginDTO.getPassword());
            redirectAttributes.addFlashAttribute("successMessage", "Login successful");
            return "redirect:/";
        } catch (UsernameNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid email or password");
            return "redirect:/login";
        }
    }
}
