package kg.coffix.app.service.implementation;

import jakarta.annotation.PostConstruct;
import kg.coffix.app.entity.User;
import kg.coffix.app.entity.enums.Role;
import kg.coffix.app.repository.UserRepository;
import kg.coffix.app.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImplementation implements UserService {

    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadUserByEmail(username);
    }

    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User has not been found"));
    }

    @PostConstruct
    void init() {
        userRepository.save(User.builder()
                        .email("user")
                        .password("$2a$12$eMrvpU2bUFEMKe8vEpLNoOgcefEDFU/VymnC4PwchTMQe3GBEsV1q")
                        .role(Role.USER)
                        .phoneNumber("1234567890")
                        .firstName("John")
                        .lastName("Doe")
                        .profileImagePath("/path/to/profile/image.jpg")
                        .enabled(true)
                        .build()

        );
    }
}
