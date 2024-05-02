package kg.coffix.app.dto.request;

import java.math.BigDecimal;

public record IngredientRequest(
        String naming,
        BigDecimal price,
        String quantityType,
        Integer quantity,
        String providerName
) {
}
