package com.example.blog.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RegisterDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

}
