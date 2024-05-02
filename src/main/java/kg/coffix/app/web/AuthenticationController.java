package kg.coffix.app.web;

import kg.coffix.app.dto.request.AuthenticationRequest;
import kg.coffix.app.dto.response.AuthenticationResponse;
import kg.coffix.app.endpoint.AuthenticationEndpoint;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationController {

    AuthenticationEndpoint authenticationEndpoint;

    @PostMapping ("/sign-in")
    public AuthenticationResponse signIn(@RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationEndpoint.authenticate(authenticationRequest);
    }

    @PostMapping("/refresh-token")
    public AuthenticationResponse generateNewAccessAndRefreshTokenByRefreshToken(
            @RequestParam(name = "refresh_token") String refresh_token) {
        return authenticationEndpoint.generateNewAccessAndRefreshTokenByRefreshToken(refresh_token);
    }
}
