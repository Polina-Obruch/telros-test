package ru.telros_test.service;

import ru.telros_test.dto.UserProfileResponseDto;
import ru.telros_test.dto.UserUpdateProfileDto;

public interface UserProfileService {

    UserProfileResponseDto updateProfile(Long userID, UserUpdateProfileDto userUpdate);

    UserProfileResponseDto getProfileByUserId(Long userId);

    void remove(Long userId);

}
