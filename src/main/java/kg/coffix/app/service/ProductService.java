package kg.coffix.app.service;

import kg.coffix.app.dto.response.MessageResponse;
import kg.coffix.app.entity.Product;

import java.util.List;

public interface ProductService {

    MessageResponse saveProduct(Product product);

    Product getProductById(Long id);

    Product getProductByNaming(String naming);

    List<Product> getAllPresent();

    MessageResponse modifyProduct(Long id, Product entity);

    MessageResponse removeProductById(Long id);

    List<String> getNameList();
}
