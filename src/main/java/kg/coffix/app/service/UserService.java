package kg.coffix.app.service;

import kg.coffix.app.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User loadUserByEmail(String email);

}
