package kg.coffix.app.entity;


import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import kg.coffix.app.entity.enums.QuantityType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@MappedSuperclass
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ingredient extends BaseEntity {

    String naming;

    BigDecimal price;

    QuantityType quantityType;

    Integer quantity;

    @ManyToOne
    Provider provider;
}
