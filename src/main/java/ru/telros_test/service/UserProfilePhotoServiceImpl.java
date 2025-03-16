package ru.telros_test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.telros_test.core.exception.EntityNotFoundException;
import ru.telros_test.entity.Image;
import ru.telros_test.entity.User;
import ru.telros_test.repository.ProfilePhotoRepository;
import ru.telros_test.repository.UserRepository;

import java.io.IOException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserProfilePhotoServiceImpl implements UserProfilePhotoService{
    private final ProfilePhotoRepository profilePhotoRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public Image addPhotoByUserId(Long userId, MultipartFile photo) throws IOException {
        User user = checkIfUserExists(userId);

        Image image = Image.builder().
                fileName(photo.getOriginalFilename())
                .mimeType(photo.getContentType())
                .user(user)
                .data(photo.getBytes())
                .build();

        return profilePhotoRepository.save(image);
    }

    @Override
    public Image getPhotoByUserId(Long userId) {
        checkIfUserExists(userId);
        return checkIfImageExists(userId);
    }

    @Transactional
    @Override
    public void removePhotoByUserId(Long userId) {
        checkIfUserExists(userId);
        checkIfImageExists(userId);
        profilePhotoRepository.deleteByUserId(userId);
    }

    @Transactional
    @Override
    public Image updatePhotoByUserId(Long userId, MultipartFile updatePhoto) throws IOException {
        Image image = checkIfImageExists(userId);
        image.setMimeType(updatePhoto.getContentType());
        image.setData(updatePhoto.getBytes());
        image.setFileName(updatePhoto.getName());
        return image;
    }

    private User checkIfUserExists(Long userId) {
       return userRepository.findById(userId).orElseThrow(()
               -> new EntityNotFoundException("User", userId));
    }

    private Image checkIfImageExists(Long userId) {
        Image image = profilePhotoRepository.getByUserId(userId);
        if (image != null) {
            return image;
        } else {
            throw new EntityNotFoundException("Photo", userId);
        }
    }
}
