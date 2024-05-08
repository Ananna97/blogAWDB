package com.example.blog.config;


import com.example.blog.dto.RegisterDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PasswordValidator implements Validator {
     @Override
    public boolean supports(Class<?> clazz) {
        return RegisterDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        RegisterDTO registerDTO = (RegisterDTO) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required", "Password is required");

        if (registerDTO.getPassword().length() < 6) {
            errors.rejectValue("password", "password.length", "Password must be at least 6 characters long");
        }

        if (!registerDTO.getPassword().matches(".*\\d.*")) {
            errors.rejectValue("password", "password.numeric", "Password must contain at least one digit");
        }

        if (!registerDTO.getPassword().matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            errors.rejectValue("password", "password.special", "Password must contain at least one special character");
        }
    }
}
