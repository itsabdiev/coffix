package kg.coffix.app.dto.request;

import java.util.List;

public record ProviderRequest(
        String fullName,
        List<String> contacts,
        String extraInfo,
        String phoneNumber,
        String imageUrl
) {
}
