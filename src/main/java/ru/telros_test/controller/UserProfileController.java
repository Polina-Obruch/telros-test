package ru.telros_test.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.telros_test.dto.UserProfileResponseDto;
import ru.telros_test.dto.UserUpdateProfileDto;
import ru.telros_test.service.UserProfileService;

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
}
