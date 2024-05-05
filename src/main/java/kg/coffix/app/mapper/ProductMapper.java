package kg.coffix.app.mapper;


import kg.coffix.app.dto.request.ProductRequest;
import kg.coffix.app.dto.response.IngredientResponse;
import kg.coffix.app.dto.response.ProductIngredientResponse;
import kg.coffix.app.dto.response.ProductResponse;
import kg.coffix.app.entity.Ingredient;
import kg.coffix.app.entity.Product;
import kg.coffix.app.entity.ProductIngredient;
import kg.coffix.app.entity.enums.ProductType;
import kg.coffix.app.entity.enums.SeasonalType;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProductMapper {
        public Product toEntity(
                ProductRequest productRequest,
                String imageUrl,
                List<ProductIngredient> ingredients
        ) {
            return Product.builder()
                    .naming(productRequest.naming())
                    .price(productRequest.price())
                    .quantity(productRequest.quantity())
                    .imageUrl(imageUrl)
                    .ingredients(ingredients)
                    .energyValue(productRequest.energyValue())
                    .benefitValue(productRequest.benefitValue())
                    .storageConditions(productRequest.storageConditions())
                    .productType(ProductType.of(productRequest.productType()))
                    .seasonalType(SeasonalType.of(productRequest.seasonalType()))
                    .build();
        }

        public ProductResponse toDto(Product entity, List<ProductIngredientResponse> ingredients) {
            return ProductResponse.builder()
                    .naming(entity.getNaming())
                    .price(entity.getPrice())
                    .quantity(entity.getQuantity())
                    .imageUrl(entity.getImageUrl())
                    .ingredients(ingredients)
                    .energyValue(entity.getEnergyValue())
                    .benefitValue(entity.getBenefitValue())
                    .storageConditions(entity.getStorageConditions())
                    .productType(entity.getProductType())
                    .seasonalType(entity.getSeasonalType())
                    .build();
        }

}
