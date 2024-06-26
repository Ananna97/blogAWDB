package com.example.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "First Name cannot be null")
    private String firstName;

    @NotNull(message = "Last Name cannot be null")
    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Pattern.List({
            @Pattern(regexp = "^(?=.*[0-9]).+$", message = "Password must contain at least one digit"),
            @Pattern(regexp = "^(?=.*[a-zA-Z]).+$", message = "Password must contain at least one letter"),
    })
    private String password;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Post> posts;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "name")})
    Set<Role> roles = new HashSet<>();


    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Rating> ratings;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;
}