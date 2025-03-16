package ru.telros_test.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.telros_test.entity.Image;
import ru.telros_test.service.UserProfilePhotoService;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users/profile/{userId}/photo")
public class UserProfilePhotoController {
    private final UserProfilePhotoService userProfilePhotoService;

    @PostMapping
    public ResponseEntity<Resource> addPhoto(@PathVariable Long userId, @RequestPart MultipartFile photo) throws IOException {
        log.info("Запрос на добавление фото в профиль пользователя");
        Image image = userProfilePhotoService.addPhotoByUserId(userId, photo);
        ByteArrayResource body = new ByteArrayResource(image.getData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, image.getMimeType())
                .body(body);
    }

    @GetMapping
    public ResponseEntity<Resource> getPhoto(@PathVariable Long userId) {
        log.info("Запрос на получение фото в профиль пользователя");
        Image image = userProfilePhotoService.getPhotoByUserId(userId);
            ByteArrayResource body = new ByteArrayResource(image.getData());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, image.getMimeType())
                    .body(body);
        }

    @PatchMapping
    public ResponseEntity<Resource> updatePhoto(@PathVariable Long userId, @RequestPart MultipartFile photo) throws IOException {
        log.info("Запрос на обновление фото в профиль пользователя");
        Image image = userProfilePhotoService.addPhotoByUserId(userId, photo);
        ByteArrayResource body = new ByteArrayResource(image.getData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, image.getMimeType())
                .body(body);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeProfilePhoto(@PathVariable Long userId) {
        log.info("Запрос на удаление фото в профиле пользователя");
        userProfilePhotoService.removePhotoByUserId(userId);
    }
}
