package ru.telros_test.service;

import org.springframework.web.multipart.MultipartFile;
import ru.telros_test.entity.Image;

import java.io.IOException;

public interface UserProfilePhotoService {

    Image addPhotoByUserId(Long userId, MultipartFile photo) throws IOException;
    Image getPhotoByUserId(Long userId);

    void removePhotoByUserId(Long userId);

    Image updatePhotoByUserId(Long userId, MultipartFile updatePhoto) throws IOException;
}
