package kg.coffix.app.dto.response;


import kg.coffix.app.entity.enums.ProductType;
import kg.coffix.app.entity.enums.SeasonalType;
import lombok.Builder;
import java.math.BigDecimal;
import java.util.List;

@Builder
public record ProductResponse(
        String naming,
        BigDecimal price,
        Integer quantity,
        String imageUrl,
        List<ProductIngredientResponse> ingredients,
        String energyValue,
        String benefitValue,
        String storageConditions,
        ProductType productType,
        SeasonalType seasonalType
) {
}
