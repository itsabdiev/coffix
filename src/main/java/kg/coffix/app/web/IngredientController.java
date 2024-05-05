package kg.coffix.app.web;


import kg.coffix.app.dto.request.IngredientRequest;
import kg.coffix.app.dto.response.MessageResponse;
import kg.coffix.app.endpoint.IngredientEndpoint;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class IngredientController {

    IngredientEndpoint ingredientEndpoint;

    @PostMapping
    public MessageResponse saveIngredient(@RequestBody IngredientRequest ingredientRequest) {
        return ingredientEndpoint.saveIngredient(ingredientRequest);
    }

    @GetMapping
    public Object getIngredientsByAttributes(
            @RequestParam(value = "id",required = false) Optional<Long> id,
            @RequestParam(value = "naming",required = false) Optional<String> naming
    ) {
        if (id.isPresent()) {
            return ingredientEndpoint.getIngredientById(id.get());
        }
        else if (naming.isPresent()) {
            return ingredientEndpoint.getIngredientByName(naming.get());
        } else {
            return ingredientEndpoint.getIngredients();
        }
    }

    @PutMapping("/{id}")
    public MessageResponse modifyIngredientById(
            @PathVariable Long id,
            @RequestBody IngredientRequest ingredientRequest
    ) {
        return ingredientEndpoint.modifyIngredient(id,ingredientRequest);
    }

    @DeleteMapping("/{id}")
    public MessageResponse removeIngredientById(@PathVariable Long id) {
        return ingredientEndpoint.removeIngredientById(id);
    }

    @GetMapping("/nameList")
    public List<String> getProvidersName() {
        return ingredientEndpoint.getIngredientsNames();
    }

}
