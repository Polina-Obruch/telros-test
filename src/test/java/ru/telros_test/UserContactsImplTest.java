package ru.telros_test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.telros_test.core.exception.EntityNotFoundException;
import ru.telros_test.dto.UserContactsResponseDto;
import ru.telros_test.dto.UserRequestDto;
import ru.telros_test.dto.mapper.UserMapper;
import ru.telros_test.entity.User;
import ru.telros_test.repository.UserRepository;
import ru.telros_test.service.UserContactsServiceImpl;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserContactsImplTest {
    @Mock
    private UserMapper userMapper;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserContactsServiceImpl service;

    private final Long userId = 1L;

    private UserContactsResponseDto userResponseDto = new UserContactsResponseDto(
            userId,
            "Иван",
            "Иванов",
            "iv@mail.com",
            "87778888888");

    private UserRequestDto userRequestDto = new UserRequestDto(
            "Иван",
            "Иванов",
            "iv@mail.com",
            "87778888888");

    private User user = new User(
            userId,
            "Иван",
            "Иванов",
            "iv@mail.com",
            "87778888888","", LocalDate.now());


    @Test
    void add_shouldReturnUserResponseDto() {
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.userRequestDtoToUser(userRequestDto)).thenReturn(user);
        when(userMapper.userToUserContactsResponseDto(user)).thenReturn(userResponseDto);

        assertThat(service.addUser(userRequestDto)).isEqualTo(userResponseDto);
    }

    @Test
    void getUserById_shouldThrowEntityNotFoundException() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.getUserById(userId)).isInstanceOf(EntityNotFoundException.class);
    }

}
