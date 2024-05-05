package kg.coffix.app.service.implementation;


import kg.coffix.app.dto.response.MessageResponse;
import kg.coffix.app.entity.Product;
import kg.coffix.app.exception.NotFoundException;
import kg.coffix.app.repository.ProductRepository;
import kg.coffix.app.service.ProductService;
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
public class ProductServiceImplementation implements ProductService {

    ProductRepository productRepository;

    @Override
    public MessageResponse saveProduct(Product product) {
        productRepository.save(product);
        return MessageResponse.builder()
                .message("Product has been saved")
                .statusCode(200)
                .build();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findPresentById(id).orElseThrow(
                () -> new NotFoundException("Product has not been found")
        );
    }

    @Override
    public Product getProductByNaming(String naming) {
        return productRepository.findPresentByNaming(naming).orElseThrow(
                () -> new NotFoundException("Product has not been found")
        );
    }

    @Override
    public List<Product> getAllPresent() {
        return productRepository.findAllPresent();
    }

    @Override
    public MessageResponse modifyProduct(Long id, Product entity) {
        getProductById(id);
        entity.setId(id);
        productRepository.save(entity);
        return  MessageResponse.builder()
                .message("Product has been modified")
                .statusCode(200)
                .build();
    }

    @Override
    public MessageResponse removeProductById(Long id) {
        getProductById(id);
        productRepository.softDeleteById(id);
        return MessageResponse.builder()
                .message("Product has been removed")
                .statusCode(200)
                .build();
    }

    @Override
    public List<String> getNameList() {
        return productRepository.fetchAllPresentNames();
    }
}
