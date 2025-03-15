package ru.telros_test.service;

import org.springframework.data.domain.Pageable;
import ru.telros_test.dto.UserContactsResponseDto;
import ru.telros_test.dto.UserRequestDto;


import java.util.List;


public interface UserContactsService {
    UserContactsResponseDto addUser(UserRequestDto user);

    UserContactsResponseDto getUserById(Long userId);

    List<UserContactsResponseDto> getUsersByIds(List<Long> userIds, Pageable page);

}
