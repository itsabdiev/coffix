package kg.coffix.app.service;

import kg.coffix.app.dto.response.MessageResponse;
import kg.coffix.app.entity.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> getIngredients();

    Ingredient getIngredientById(Long id);

    MessageResponse saveIngredient(Ingredient ingredient);

    Ingredient getIngredientByNaming(String naming);

    MessageResponse modifyIngredientById(Long id, Ingredient entity);

    MessageResponse removeById(Long id);

    List<String> getIngredientsNames();

}
