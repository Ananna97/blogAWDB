package com.example.blog.controller;

import com.example.blog.config.PasswordValidator;
import com.example.blog.dto.RegisterDTO;
import com.example.blog.model.User;
import com.example.blog.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class RegisterController {
    private final UserService userService;
    private final PasswordValidator passValidator;

    public RegisterController(UserService userService, PasswordValidator passValidator) {
        this.userService = userService;
        this.passValidator = passValidator;
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        model.addAttribute("registerDTO", new RegisterDTO());
        return "register";
    }
    @InitBinder("registerDTO")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(passValidator);
    }
    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute("registerDTO") RegisterDTO registerDTO, BindingResult bindingResult) {
        passValidator.validate(registerDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "register";
        }
        User user = new User();
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        userService.save(user);
        return "redirect:/";
    }

}
