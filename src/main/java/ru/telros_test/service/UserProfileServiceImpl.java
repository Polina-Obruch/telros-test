package ru.telros_test.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.telros_test.core.exception.EntityNotFoundException;
import ru.telros_test.dto.UserProfileResponseDto;
import ru.telros_test.dto.UserUpdateProfileDto;
import ru.telros_test.dto.mapper.UserMapper;
import ru.telros_test.entity.User;
import ru.telros_test.repository.UserRepository;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserProfileResponseDto updateProfile(Long userId, UserUpdateProfileDto userUpdate) {
        //Проверка наличия пользователя, которого хотим обновить
        User user = userRepository.findById(userId).orElseThrow(()
                -> new EntityNotFoundException("User", userId));

        String name = userUpdate.getName();
        if (name != null && !name.isBlank()) {
            user.setName(name);
        }

        String lastname = userUpdate.getLastname();
        if (lastname != null && !lastname.isBlank()) {
            user.setLastname(lastname);
        }

        String email = userUpdate.getEmail();
        if (email != null && !email.isBlank()) {
            user.setEmail(email);
        }

        String phone = userUpdate.getPhone();
        if (phone != null && !phone.isBlank()) {
            user.setPhone(phone);
        }

        String patronymic = userUpdate.getPatronymic();
        if (patronymic != null && !patronymic.isBlank()) {
            user.setPatronymic(patronymic);
        }

        LocalDate birthdate = userUpdate.getBirthdate();
        if (birthdate != null) {
            user.setBirthdate(birthdate);
        }

        return userMapper.userToUserProfileResponseDto(user);
    }

    @Override
    public UserProfileResponseDto getProfileByUserId(Long userId) {
        return userMapper.userToUserProfileResponseDto(userRepository.findById(userId).orElseThrow(()
                -> new EntityNotFoundException("User", userId)));
    }

    @Transactional
    @Override
    public void remove(Long userId) {
        userRepository.findById(userId).orElseThrow(()
                -> new EntityNotFoundException("User", userId));
        userRepository.deleteById(userId);
    }
}
