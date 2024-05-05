package kg.coffix.app.entity;


import jakarta.persistence.*;
import kg.coffix.app.entity.enums.ProductType;
import kg.coffix.app.entity.enums.SeasonalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends BaseEntity {

    String naming;

    BigDecimal price;

    Integer quantity;

    String imageUrl;

    @ManyToMany
    List<ProductIngredient> ingredients;

    String energyValue;

    String benefitValue;

    String storageConditions;

    @Enumerated(value = EnumType.STRING)
    ProductType productType;

    @Enumerated(value = EnumType.STRING)
    SeasonalType seasonalType;
}
