package kg.coffix.app.service.implementation;


import jakarta.annotation.PostConstruct;
import kg.coffix.app.dto.response.MessageResponse;
import kg.coffix.app.entity.Provider;
import kg.coffix.app.exception.NotFoundException;
import kg.coffix.app.repository.ProviderRepository;
import kg.coffix.app.service.ProviderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProviderServiceImplementation implements ProviderService {

    ProviderRepository providerRepository;

    @Override
    public List<Provider> getAllProviders() {
        return providerRepository.findAllPresent();
    }

    @Override
    public MessageResponse saveProvider(Provider provider) {
        providerRepository.save(provider);
        return MessageResponse.builder()
                .message("Provider has been saved")
                .statusCode(200)
                .build();
    }

    @Override
    public Provider getProviderById(Long id) {
        return providerRepository.findPresentById(id).orElseThrow(
                () -> new NotFoundException("Provider has not been found")
        );
    }

    @Override
    public Provider getProviderByProviderName(String name) {
        return providerRepository.findByFullName(name).orElseThrow(
                () -> new NotFoundException("Provider has not been found")
        );
    }

    @Override
    public MessageResponse modifyProvider(Long id, Provider entity) {
        getProviderById(id);
        entity.setId(id);
        providerRepository.save(entity);
        return MessageResponse.builder()
                .message("Provider has been modified")
                .statusCode(200)
                .build();
    }

    @Override
    public MessageResponse removeProviderById(Long id) {
        getProviderById(id);
        providerRepository.softDeleteById(id);
        return MessageResponse.builder()
                .message("Provider has been removed")
                .statusCode(200)
                .build();
    }

    @Override
    public List<String> getProvidersName() {
        return providerRepository.fetchAllPresentNames();
    }

    @PostConstruct
    void init() {
        Provider provider1 = Provider.builder()
                .fullName("Aroma LLC")
                .contacts(List.of("Brazil,Brinch Avenue"))
                .extraInfo("Brazil Inc")
                .phoneNumber("+99670850455")
                .hasBeenRemoved(false)
                .imageUrl("https://cblproperty.blob.core.windows.net/production/assets/bltbf752684e367ea4a-ArabicaCoffee_Logo.png")
                .build();

        Provider provider2 = Provider.builder()
                .fullName("Bencih LLC")
                .contacts(List.of("Kazakhstan,Battyr Avenue"))
                .extraInfo("KTZHs")
                .phoneNumber("+99670850455")
                .hasBeenRemoved(false)
                .imageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJDXS4JoesQpTqAuzlko4JEf3glQxd_RuEfAy74UaeAg&s")
                .build();

        Provider provider3 = Provider.builder()
                .fullName("Bencih LLC")
                .contacts(List.of("Kazakhstan,Battyr Avenue"))
                .extraInfo("KTZHs")
                .phoneNumber("+99670850455")
                .hasBeenRemoved(false)
                .imageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJDXS4JoesQpTqAuzlko4JEf3glQxd_RuEfAy74UaeAg&s")
                .build();

        Provider provider4 = Provider.builder()
                .fullName("Bencih LLC")
                .contacts(List.of("Kazakhstan,Battyr Avenue"))
                .extraInfo("KTZHs")
                .phoneNumber("+99670850455")
                .hasBeenRemoved(false)
                .imageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJDXS4JoesQpTqAuzlko4JEf3glQxd_RuEfAy74UaeAg&s")
                .build();
        providerRepository.saveAll(List.of(provider2,provider1,provider4,provider3));
    }
}
