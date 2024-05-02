package kg.coffix.app.web;

import kg.coffix.app.entity.enums.SeasonalType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/seasonal-types")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SeasonalTypeController {

    @GetMapping
    public List<String> getSeasonalTypes() {
        return Arrays.stream(SeasonalType.values()).map(Enum::name).collect(Collectors.toList());
    }
}
