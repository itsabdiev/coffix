package kg.coffix.app.dto.request;

public record AuthenticationRequest(
        String email,
        String password
) {
}
