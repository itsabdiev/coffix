package kg.coffix.app.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
public class Product extends BaseEntity{

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
