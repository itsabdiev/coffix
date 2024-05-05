package kg.coffix.app.web;


import kg.coffix.app.dto.request.IngredientRequest;
import kg.coffix.app.dto.request.ProductRequest;
import kg.coffix.app.dto.response.MessageResponse;
import kg.coffix.app.endpoint.ProductEndpoint;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductController {

    ProductEndpoint productEndpoint;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MessageResponse saveProduct(@ModelAttribute ProductRequest productRequest) {
        return productEndpoint.saveProduct(productRequest);
    }

    @GetMapping
    public Object getProductsByAttributes(
            @RequestParam(value = "id",required = false) Optional<Long> id,
            @RequestParam(value = "naming",required = false) Optional<String> naming
    ) {
        if (id.isPresent()) {
            return productEndpoint.getProductById(id.get());
        }
        else if (naming.isPresent()) {
            return productEndpoint.getProductByName(naming.get());
        } else {
            return productEndpoint.getProducts();
        }
    }

    @PutMapping(value = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MessageResponse modifyProduct(
            @PathVariable Long id,
            @ModelAttribute ProductRequest productRequest
    ) {
        return productEndpoint.modifyProduct(id,productRequest);
    }

    @DeleteMapping("/{id}")
    public MessageResponse removeProductById(@PathVariable Long id) {
        return productEndpoint.removeProductById(id);
    }

    @GetMapping("/nameList")
    public List<String> getProductNames() {
        return productEndpoint.getProductNames();
    }


}
