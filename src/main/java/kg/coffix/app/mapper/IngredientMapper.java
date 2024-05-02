package kg.coffix.app.mapper;

import kg.coffix.app.dto.request.IngredientRequest;
import kg.coffix.app.dto.response.IngredientResponse;
import kg.coffix.app.dto.response.ProviderResponse;
import kg.coffix.app.entity.Ingredient;
import kg.coffix.app.entity.Provider;
import kg.coffix.app.entity.enums.QuantityType;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class IngredientMapper {



    public Ingredient toEntity(IngredientRequest ingredientRequest, Provider provider,QuantityType quantityType) {
        return Ingredient.builder()
                .quantity(ingredientRequest.quantity())
                .naming(ingredientRequest.naming())
                .price(ingredientRequest.price())
                .provider(provider)
                .quantityType(quantityType)
                .build();
    }

    public IngredientResponse toDto(Ingredient ingredient, ProviderResponse providerResponse) {
        return IngredientResponse.builder()
                .id(ingredient.getId())
                .quantityType(ingredient.getQuantityType())
                .naming(ingredient.getNaming())
                .providerResponse(providerResponse)
                .quantity(ingredient.getQuantity())
                .price(ingredient.getPrice())
                .build();
    }

}
