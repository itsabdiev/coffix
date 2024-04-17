package kg.coffix.app.dto.response;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String token,
        String refresh_token
) {
}
