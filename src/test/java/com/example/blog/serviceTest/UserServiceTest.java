package com.example.blog.serviceTest;

import com.example.blog.model.Role;
import com.example.blog.model.User;
import com.example.blog.repository.RoleRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.UserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@ActiveProfiles("test")
public class UserServiceTest {

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void testSave() {
        UserService userService = new UserService(userRepository, passwordEncoder, roleRepository);

        User userToSave = new User();
        userToSave.setFirstName("Diana");
        userToSave.setLastName("Visan");
        userToSave.setEmail("test@example.com");
        userToSave.setPassword("password");

        Role role = new Role();
        role.setName("ROLE_USER");
        when(roleRepository.findById("ROLE_USER")).thenReturn(Optional.of(role));

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        when(passwordEncoder.encode("password")).thenReturn("hashedPassword");

        User savedUser = userService.save(userToSave);

        assertEquals("test@example.com", savedUser.getEmail());
        assertEquals("hashedPassword", savedUser.getPassword());
        assertTrue(savedUser.getRoles().contains(role));
    }

    @Test
    public void testFindByEmail() {
        User sampleUser = new User();
        sampleUser.setFirstName("Diana");
        sampleUser.setLastName("Visan");
        sampleUser.setEmail("test@example.com");
        sampleUser.setPassword("somepasss123");
        userRepository.save(sampleUser);

        Optional<User> foundUser = userRepository.findByEmail("test@example.com");

        assertTrue(foundUser.isPresent());
        assertEquals("test@example.com", foundUser.get().getEmail());
    }
}

