package kg.coffix.app.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "product_ingredients")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductIngredient extends BaseEntity {

    @ManyToOne
    Ingredient ingredient;

    @Column(nullable = false)
    Integer quantity;
}
