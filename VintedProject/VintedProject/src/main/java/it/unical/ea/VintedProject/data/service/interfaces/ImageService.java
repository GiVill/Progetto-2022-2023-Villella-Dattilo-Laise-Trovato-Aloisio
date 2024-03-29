package it.unical.ea.VintedProject.data.service.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    Resource getImage(String imagePath);

    Boolean insertUserImage(MultipartFile img);

    Boolean insertInsertionImage(Long insertionId, MultipartFile img);

    void deleteImageUser(Long userId);

    void adminDeleteImageInsertion(Long insertionId);
    void userDeleteImageInsertion(Long insertionId);
}
