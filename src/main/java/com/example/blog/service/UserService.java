package com.example.blog.service;

import com.example.blog.exceptions.UserNotFoundException;
import com.example.blog.model.Role;
import com.example.blog.model.User;
import com.example.blog.repository.RoleRepository;
import com.example.blog.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;




@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {

        if (user.getId() == null) {
            if (user.getRoles().isEmpty()) {
                Set<Role> roles = new HashSet<>();
                roleRepository.findById("ROLE_USER").ifPresent(roles::add);
                user.setRoles(roles);
            }
            user.setCreatedAt(LocalDateTime.now());
        }
        user.setUpdatedAt(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Saved user: {}", user.toString());
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email)
    {
        log.info("Find user by email: {}", email);
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found with email: " + email);
        }
        return optionalUser;
    }

    public void authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
    }

    public void register(User user, String roleName) {
        if (user.getRoles().isEmpty()) {
            Set<Role> roles = new HashSet<>();
            roleRepository.findById("ROLE_USER").ifPresent(roles::add);
            user.setRoles(roles);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        userRepository.save(user);
    }
}