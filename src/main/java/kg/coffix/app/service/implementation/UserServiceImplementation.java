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
                        .firstName("User")
                        .lastName("Userov")
                        .profileImagePath("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT-NSEUHWmQsGxt4SfVM3f8VMW7vN8JsHnL-CnVII5E4A&s")
                        .enabled(true)
                        .hasBeenRemoved(false)
                        .build()
        );

        userRepository.save(User.builder()
                .email("manager")
                .password("$2a$12$ot500PKFu7Ov5kfpLGbVLOU4dQov2eRURL8X6J3dQQbtHjXons4RO")
                .role(Role.MANAGER)
                .phoneNumber("1234567890")
                .firstName("Manager")
                .lastName("Managerov")
                .profileImagePath("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT-NSEUHWmQsGxt4SfVM3f8VMW7vN8JsHnL-CnVII5E4A&s")
                .enabled(true)
                .hasBeenRemoved(false)
                .build()
        );

        userRepository.save(User.builder()
                .email("admin")
                .password("$2a$12$YURgoMXZO8NAfOVBp7rzp.HZaAjEj/xKI9gnuBF2Iif5wv84DXT2e")
                .role(Role.ADMIN)
                .phoneNumber("1234567890")
                .firstName("Admin")
                .lastName("Adminov")
                .profileImagePath("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT-NSEUHWmQsGxt4SfVM3f8VMW7vN8JsHnL-CnVII5E4A&s")
                .enabled(true)
                .hasBeenRemoved(false)
                .build()
        );
    }
}
