package kg.coffix.app.service;

import kg.coffix.app.entity.ProductIngredient;

import java.util.List;

public interface ProductIngredientService {
    void saveProduct(ProductIngredient productIngredient);
    List<ProductIngredient> saveAll(List<ProductIngredient> productIngredientList);
}
