package kg.coffix.app.configuration;

import com.cloudinary.Cloudinary;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CloudinaryConfiguration {

    @Value("${cloudinary.config-url}")
    String cloudinaryConfigUrl;


    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary((cloudinaryConfigUrl));
    }
}
