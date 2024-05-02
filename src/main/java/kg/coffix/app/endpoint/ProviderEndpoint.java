package kg.coffix.app.endpoint;


import kg.coffix.app.dto.request.ProviderRequest;
import kg.coffix.app.dto.response.MessageResponse;
import kg.coffix.app.dto.response.ProviderResponse;
import kg.coffix.app.mapper.ProviderMapper;
import kg.coffix.app.service.ProviderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProviderEndpoint {

    ProviderService providerService;
    ProviderMapper providerMapper;

    public List<ProviderResponse> getAllProviders() {
        return providerMapper.toDtoList(providerService.getAllProviders());
    }

    public MessageResponse saveProvider(ProviderRequest providerRequest) {
        return providerService.saveProvider(providerMapper.toEntity(providerRequest));
    }

    public ProviderResponse getProviderById(Long id) {
        return providerMapper.toDto(providerService.getProviderById(id));
    }

    public MessageResponse modifyProvider(Long id, ProviderRequest providerRequest) {
        return providerService.modifyProvider(id,providerMapper.toEntity(providerRequest));
    }

    public MessageResponse removeProviderById(Long id) {
        return providerService.removeProviderById(id);
    }

    public List<String> getProvidersName() {
        return providerService.getProvidersName();
    }

    public ProviderResponse getProviderByName(String fullName) {
        return providerMapper.toDto(providerService.getProviderByProviderName(fullName));
    }
}
