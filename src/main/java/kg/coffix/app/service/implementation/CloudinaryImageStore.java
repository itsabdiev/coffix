package kg.coffix.app.service.implementation;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import kg.coffix.app.exception.BadRequestException;
import java.util.UUID;
import kg.coffix.app.service.ImageStorageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CloudinaryImageStore implements ImageStorageService {

    Cloudinary cloudinary;

    public List<String> uploadImages(MultipartFile[] files) {
        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            urls.add(uploadImage(file));
        }
        return urls;
    }

    public String uploadImage(MultipartFile file) {
        checkImage(file);
        try {
            return (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                    "folder","all",
                    "public_id", getRandomUUID(),
                    "unique_filename", "true"
            )).get("secure_url");
        }catch (IOException ioe) {
            throw new BadRequestException("Image does not meet requirements");
        }
    }

    private void checkImage(MultipartFile file) {
        if (file == null) throw new BadRequestException("Image does not meet requirements");
        if (!Arrays.asList(ContentType.IMAGE_GIF.getMimeType(),
                        ContentType.IMAGE_JPEG.getMimeType(),
                        ContentType.IMAGE_PNG.getMimeType(),
                        ContentType.IMAGE_SVG.getMimeType(),
                        ContentType.IMAGE_WEBP.getMimeType()).
                contains(file.getContentType()))
            throw new BadRequestException("Image does not meet requirements");
        if (file.isEmpty()) throw new BadRequestException("Image does not meet requirements");
    }

    private String getRandomUUID() {
        String uniqueUUID = String.valueOf(UUID.randomUUID()) + UUID.randomUUID() + UUID.randomUUID();
        return uniqueUUID.replaceAll("[^a-zA-z0-9]","");
    }

}
