package kg.coffix.app.dto.response;


import lombok.Builder;

@Builder
public record ProductIngredientResponse(
    IngredientResponse ingredientResponse,
    Integer quantity
) {

}
