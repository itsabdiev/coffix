package kg.coffix.app.dto.response;


import lombok.Builder;

import java.util.List;

@Builder
public record ProviderResponse(
        Long id,
        String fullName,
        List<String> contacts,
        String extraInfo,
        String phoneNumber,
        String imageUrl
) {
}
