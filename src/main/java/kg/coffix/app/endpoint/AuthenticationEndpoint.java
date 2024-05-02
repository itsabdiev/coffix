package kg.coffix.app.endpoint;


import io.jsonwebtoken.JwtException;
import kg.coffix.app.dto.request.AuthenticationRequest;
import kg.coffix.app.dto.response.AuthenticationResponse;
import kg.coffix.app.entity.User;
import kg.coffix.app.security.util.JsonWebTokenService;
import kg.coffix.app.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationEndpoint {

    AuthenticationManager authenticationManager;
    UserService userService;
    JsonWebTokenService jsonWebTokenService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        User user = userService.loadUserByEmail(request.email());
        String token = jsonWebTokenService.generateToken(user);
        String refreshToken = jsonWebTokenService.generateRefreshToken(user.getEmail());
        return AuthenticationResponse.builder()
                .token(token)
                .refresh_token(refreshToken)
                .build();
    }


    public AuthenticationResponse generateNewAccessAndRefreshTokenByRefreshToken(String refreshToken) {
        if (jsonWebTokenService.isTokenExpired(refreshToken)) {
            throw new JwtException("Refresh has been expired");
        }
        String email = jsonWebTokenService.extractUsername(refreshToken);
        User user = userService.loadUserByEmail(email);
        return AuthenticationResponse.builder()
                .token(jsonWebTokenService.generateToken(user))
                .refresh_token(jsonWebTokenService.generateRefreshToken(email))
                .build();
    }

}
