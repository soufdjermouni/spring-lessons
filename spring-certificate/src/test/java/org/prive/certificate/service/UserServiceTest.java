package org.prive.certificate.service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.prive.certificate.entity.User;
import org.prive.certificate.repository.UserRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.zip.DataFormatException;

@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testCreateUser() throws DataFormatException {
        User user = new User(1L, "John Doe", "john@example.com");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.createUser(user);
        assert(savedUser.getId() != null);
    }
}