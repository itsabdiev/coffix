package kg.coffix.app.entity;


import jakarta.persistence.OneToMany;
import kg.coffix.app.entity.enums.ProductType;
import kg.coffix.app.entity.enums.SeasonalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;


@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    String naming;

    BigDecimal price;

    Integer quantity;

    String imageUrl;

    @OneToMany
    List<Ingredient> ingredients;

    String energyValue;

    String benefitValue;

    String storageConditions;

    ProductType productType;

    SeasonalType seasonalType;
}
