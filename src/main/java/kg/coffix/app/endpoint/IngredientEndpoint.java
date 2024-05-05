package kg.coffix.app.endpoint;


import kg.coffix.app.dto.request.IngredientRequest;
import kg.coffix.app.dto.response.IngredientResponse;
import kg.coffix.app.dto.response.MessageResponse;
import kg.coffix.app.entity.Ingredient;
import kg.coffix.app.entity.enums.QuantityType;
import kg.coffix.app.mapper.IngredientMapper;
import kg.coffix.app.mapper.ProviderMapper;
import kg.coffix.app.service.IngredientService;
import kg.coffix.app.service.ProviderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IngredientEndpoint {

    IngredientService ingredientService;
    IngredientMapper ingredientMapper;
    ProviderMapper providerMapper;
    ProviderService providerService;

    public List<IngredientResponse> getIngredients() {
        return ingredientService.getIngredients()
                .stream()
                .map(
                        x -> ingredientMapper.toDto(x, providerMapper.toDto(x.getProvider()))
                )
                .collect(Collectors.toList());
    }
    public IngredientResponse getIngredientById(Long id) {
        Ingredient ingredientById = ingredientService.getIngredientById(id);
        return ingredientMapper.toDto(ingredientById,providerMapper.toDto(ingredientById.getProvider()));
    }

    public MessageResponse saveIngredient(IngredientRequest ingredientRequest) {
        Ingredient entity = ingredientMapper.toEntity(
                ingredientRequest,
                providerService.getProviderByProviderName(ingredientRequest.providerName()),
                QuantityType.of(ingredientRequest.quantityType()));
        return ingredientService.saveIngredient(entity);
    }



    public IngredientResponse getIngredientByName(String naming) {
        Ingredient ingredientByNaming = ingredientService.getIngredientByNaming(naming);
        return ingredientMapper.toDto(ingredientByNaming,providerMapper.toDto(ingredientByNaming.getProvider()));
    }

    public MessageResponse modifyIngredient(Long id, IngredientRequest ingredientRequest) {
        return ingredientService.modifyIngredientById(
                id,
                ingredientMapper.toEntity(
                ingredientRequest,
                providerService.getProviderByProviderName(ingredientRequest.providerName()),
                QuantityType.of(ingredientRequest.quantityType())));
    }


    public MessageResponse removeIngredientById(Long id) {
        return ingredientService.removeById(id);
    }

    public List<String> getIngredientsNames() {
        return ingredientService.getIngredientsNames();
    }
}
