package kg.coffix.app.service.implementation;

import kg.coffix.app.entity.ProductIngredient;
import kg.coffix.app.repository.ProductIngredientRepository;
import kg.coffix.app.service.ProductIngredientService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductIngredientServiceImplementation implements ProductIngredientService {

    ProductIngredientRepository productIngredientRepository;
    @Override
    public void saveProduct(ProductIngredient productIngredient) {
        productIngredientRepository.save(productIngredient);
    }

    @Override
    public List<ProductIngredient> saveAll(List<ProductIngredient> productIngredientList) {
        return productIngredientRepository.saveAll(productIngredientList);
    }
}
