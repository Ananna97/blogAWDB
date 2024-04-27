package com.example.blog.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RegisterDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

}
