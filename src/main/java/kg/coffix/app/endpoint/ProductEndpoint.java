package kg.coffix.app.endpoint;


import kg.coffix.app.dto.request.ProductRequest;
import kg.coffix.app.dto.response.MessageResponse;
import kg.coffix.app.dto.response.ProductIngredientResponse;
import kg.coffix.app.dto.response.ProductResponse;
import kg.coffix.app.entity.Product;
import kg.coffix.app.entity.ProductIngredient;
import kg.coffix.app.mapper.IngredientMapper;
import kg.coffix.app.mapper.ProductIngredientMapper;
import kg.coffix.app.mapper.ProductMapper;
import kg.coffix.app.mapper.ProviderMapper;
import kg.coffix.app.service.ImageStorageService;
import kg.coffix.app.service.IngredientService;
import kg.coffix.app.service.ProductIngredientService;
import kg.coffix.app.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductEndpoint {

    ImageStorageService imageStorageService;
    ProductService productService;
    IngredientService ingredientService;
    ProductIngredientService productIngredientService;
    ProductMapper productMapper;
    ProductIngredientMapper productIngredientMapper;
    IngredientMapper ingredientMapper;
    ProviderMapper providerMapper;

    @Transactional
    public MessageResponse saveProduct(ProductRequest productRequest) {
        Pair<String, List<ProductIngredient>> extras = getExtras(productRequest);
        return productService.saveProduct(
                productMapper.toEntity(
                        productRequest,
                        extras.getLeft(),
                        extras.getRight()
                )
        );
    }


    public ProductResponse getProductById(Long id) {
        Product productById = productService.getProductById(id);
        return getProduct(productById);
    }


    public ProductResponse getProductByName(String naming) {
        Product productByNaming = productService.getProductByNaming(naming);
        return getProduct(productByNaming);
    }

    public ProductResponse getProduct(Product product) {
        List<ProductIngredientResponse> productIngredientResponseList = product.getIngredients().stream().map(
                x -> productIngredientMapper
                        .toDto(x, ingredientMapper
                                .toDto(x.getIngredient(),
                                        providerMapper.toDto(x.getIngredient().getProvider()
                                        )
                                )
                        )
        ).toList();
        return productMapper.toDto(product,productIngredientResponseList);
    }

    public List<ProductResponse> getProducts() {
        return productService.getAllPresent().stream().map(
                this::getProduct
        ).collect(Collectors.toList());
    }


    public MessageResponse modifyProduct(Long id, ProductRequest productRequest) {
        Pair<String, List<ProductIngredient>> extras = getExtras(productRequest);
        return productService.modifyProduct(id,
                productMapper.toEntity(
                        productRequest,
                        extras.getLeft(),
                        extras.getRight()
                )
        );
    }

    private Pair<String,List<ProductIngredient>> getExtras(ProductRequest productRequest) {
        String imageUrl = imageStorageService.uploadImage(productRequest.image());
        List<ProductIngredient> productIngredients = productRequest.ingredients().stream()
                .map(ingredient -> ProductIngredient.builder()
                        .ingredient(ingredientService.getIngredientByNaming(ingredient.name()))
                        .quantity(ingredient.quantity())
                        .build())
                .collect(Collectors.toList());
        List<ProductIngredient> _productIngredients = productIngredientService.saveAll(productIngredients);
        return Pair.of(imageUrl,_productIngredients);
    }

    public MessageResponse removeProductById(Long id) {
        return productService.removeProductById(id);
    }

    public List<String> getProductNames() {
        return productService.getNameList();
    }
}
