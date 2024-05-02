package kg.coffix.app.service;

import kg.coffix.app.dto.response.MessageResponse;
import kg.coffix.app.entity.Provider;

import java.util.List;

public interface ProviderService {
    List<Provider> getAllProviders();

    MessageResponse saveProvider(Provider entity);

    Provider getProviderById(Long id);

    Provider getProviderByProviderName(String name);

    MessageResponse modifyProvider(Long id, Provider entity);

    MessageResponse removeProviderById(Long id);

    List<String> getProvidersName();

}
