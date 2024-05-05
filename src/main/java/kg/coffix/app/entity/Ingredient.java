package kg.coffix.app.entity;

import jakarta.persistence.*;
import kg.coffix.app.entity.enums.QuantityType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import java.math.BigDecimal;

@Entity
@Table(name = "ingredients")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ingredient extends BaseEntity {

    String naming;

    BigDecimal price;

    @Enumerated(value = EnumType.STRING)
    QuantityType quantityType;

    Integer quantity;

    @ManyToOne
    Provider provider;
}
