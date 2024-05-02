package kg.coffix.app.service.implementation;


import kg.coffix.app.dto.response.MessageResponse;
import kg.coffix.app.entity.Ingredient;
import kg.coffix.app.exception.NotFoundException;
import kg.coffix.app.repository.IngredientRepository;
import kg.coffix.app.service.IngredientService;
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
public class IngredientServiceImplementation implements IngredientService {

    IngredientRepository ingredientRepository;

    @Override
    public List<Ingredient> getIngredients() {
        return ingredientRepository.findAllPresent();
    }

    @Override
    public Ingredient getIngredientById(Long id) {
        return ingredientRepository.findPresentById(id).orElseThrow(
                () -> new NotFoundException("Ingredient has not been found")
        );
    }

    @Override
    public MessageResponse saveIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
        return MessageResponse.builder()
                .message("Ingredient has been saved")
                .statusCode(200)
                .build();
    }

    @Override
    public Ingredient getIngredientByNaming(String naming) {
        return ingredientRepository.findByNaming(naming).orElseThrow(
                () -> new NotFoundException("Ingredient has not been found")
        );
    }

    @Override
    public MessageResponse modifyIngredientById(Long id, Ingredient entity) {
        getIngredientById(id);
        entity.setId(id);
        ingredientRepository.save(entity);
        return  MessageResponse.builder()
                .message("Ingredient has been modified")
                .statusCode(200)
                .build();
    }

    @Override
    public MessageResponse removeById(Long id) {
        getIngredientById(id);
        ingredientRepository.softDeleteById(id);
        return MessageResponse.builder()
                .message("Ingredient has been removed")
                .statusCode(200)
                .build();
    }

    @Override
    public List<String> getIngredientsNames() {
        return ingredientRepository.fetchAllPresentNames();
    }
}
