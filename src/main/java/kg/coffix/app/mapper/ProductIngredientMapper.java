package kg.coffix.app.mapper;

import kg.coffix.app.dto.response.IngredientResponse;
import kg.coffix.app.dto.response.ProductIngredientResponse;
import kg.coffix.app.entity.ProductIngredient;
import org.springframework.stereotype.Component;

@Component
public class ProductIngredientMapper {

        public ProductIngredientResponse toDto(ProductIngredient dto, IngredientResponse ingredientResponse) {
            return ProductIngredientResponse.builder()
                    .ingredientResponse(ingredientResponse)
                    .quantity(dto.getQuantity())
                    .build();
        }
}
