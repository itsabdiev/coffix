package kg.coffix.app.dto.response;

import kg.coffix.app.entity.enums.QuantityType;
import lombok.Builder;

import java.math.BigDecimal;


@Builder
public record IngredientResponse(
        Long id,
        String naming,
        BigDecimal price,
        QuantityType quantityType,
        Integer quantity,
        ProviderResponse providerResponse
) {
}
