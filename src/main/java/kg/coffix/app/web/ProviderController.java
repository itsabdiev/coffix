package kg.coffix.app.web;


import kg.coffix.app.dto.request.ProviderRequest;
import kg.coffix.app.dto.response.MessageResponse;
import kg.coffix.app.dto.response.ProviderResponse;
import kg.coffix.app.endpoint.ProviderEndpoint;
import kg.coffix.app.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/providers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProviderController {

    ProviderEndpoint providerEndpoint;

    @GetMapping
    public Object getProvidersByAttributes(
            @RequestParam(value = "id",required = false) Optional<Long> id,
            @RequestParam(value = "fullName",required = false) Optional<String> fullName
    ) {
        if (id.isPresent()) {
            return providerEndpoint.getProviderById(id.get());
        }
        else if (fullName.isPresent()) {
            return providerEndpoint.getProviderByName(fullName.get());
        } else {
            return providerEndpoint.getAllProviders();
        }
    }

    @PostMapping
    public MessageResponse saveProvider(@RequestBody ProviderRequest providerRequest) {
        return providerEndpoint.saveProvider(providerRequest);
    }

    @PutMapping("/{id}")
    public MessageResponse modifyProvider(@PathVariable Long id,@RequestBody ProviderRequest providerRequest) {
        return providerEndpoint.modifyProvider(id,providerRequest);
    }

    @DeleteMapping("/{id}")
    public MessageResponse removeProvider(@PathVariable Long id) {
        return providerEndpoint.removeProviderById(id);
    }

    @GetMapping("/nameList")
    public List<String> getProvidersName() {
        return providerEndpoint.getProvidersName();
    }

}
