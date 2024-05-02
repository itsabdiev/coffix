package kg.coffix.app.mapper;

import kg.coffix.app.dto.request.ProviderRequest;
import kg.coffix.app.dto.response.ProviderResponse;
import kg.coffix.app.entity.Provider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class ProviderMapper {

    public ProviderResponse toDto(Provider provider) {
        return ProviderResponse.builder()
                .id(provider.getId())
                .contacts(provider.getContacts())
                .extraInfo(provider.getExtraInfo())
                .fullName(provider.getFullName())
                .phoneNumber(provider.getPhoneNumber())
                .imageUrl(provider.getImageUrl())
                .build();
    }

    public Provider toEntity(ProviderRequest providerRequest) {
        return Provider.builder()
                .contacts(providerRequest.contacts())
                .fullName(providerRequest.fullName())
                .extraInfo(providerRequest.extraInfo())
                .phoneNumber(providerRequest.phoneNumber())
                .imageUrl(providerRequest.imageUrl())
                .build();
    }

    public List<ProviderResponse> toDtoList(List<Provider> providers) {
        return providers.stream().map(this::toDto).collect(Collectors.toList());
    }

}
