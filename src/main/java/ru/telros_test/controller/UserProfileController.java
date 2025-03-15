package ru.telros_test.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.telros_test.dto.UserProfileResponseDto;
import ru.telros_test.dto.UserUpdateProfileDto;
import ru.telros_test.service.UserProfileService;

//Контроллер для работы с полной информацией о пользователе
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users/profile/{userId}")
public class UserProfileController {
    private final UserProfileService userProfileService;

    @PatchMapping
    public UserProfileResponseDto updateProfile(@PathVariable Long userId, @Valid @RequestBody UserUpdateProfileDto userUpdate) {
        log.info("Запрос на обновление профиля пользователя");
        return userProfileService.updateProfile(userId, userUpdate);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeUser(@PathVariable Long userId) {
        log.info("Запрос на удаление пользователя");
        userProfileService.remove(userId);
    }

    @GetMapping
    public UserProfileResponseDto getUserById(@PathVariable Long userId) {
        log.info("Запрос на выдачу профиля пользователя по ID");
        return userProfileService.getProfileByUserId(userId);
    }
}
