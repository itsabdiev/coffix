package kg.coffix.app.dto.request;

import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;
import java.util.List;

public record ProductRequest(
        String naming,
        BigDecimal price,
        Integer quantity,
        MultipartFile image,
        List<IngredientInfo> ingredients,
        String energyValue,
        String benefitValue,
        String storageConditions,
        String productType,
        String seasonalType
) {
}
