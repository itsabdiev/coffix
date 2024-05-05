package kg.coffix.app.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kg.coffix.app.dto.request.IngredientInfo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class IngredientInfoConverter implements Converter<String, List<IngredientInfo>> {

    ObjectMapper objectMapper;

    @Override
    public List<IngredientInfo> convert(String source) {
        try {
            return objectMapper.readValue(source, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
