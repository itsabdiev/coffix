package kg.coffix.app.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageStorageService {

    String uploadImage(MultipartFile file);

    List<String> uploadImages(MultipartFile[] files);


}
