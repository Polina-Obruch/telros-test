package ru.telros_test.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.telros_test.core.exception.EntityNotFoundException;
import ru.telros_test.dto.UserContactsResponseDto;
import ru.telros_test.dto.UserRequestDto;
import ru.telros_test.dto.mapper.UserMapper;
import ru.telros_test.entity.User;
import ru.telros_test.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
public class UserContactsServiceImpl implements UserContactsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserContactsServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    @Override
    public UserContactsResponseDto addUser(UserRequestDto userDto) {
        return userMapper.userToUserContactsResponseDto(
                userRepository.save(userMapper.userRequestDtoToUser(userDto)));
    }

    @Transactional
    @Override
    public void remove(Long userId) {
        userRepository.findById(userId).orElseThrow(()
                -> new EntityNotFoundException("User", userId));
        userRepository.deleteById(userId);
    }

    @Override
    public UserContactsResponseDto getUserById(Long userId) {
        return userMapper.userToUserContactsResponseDto(
                userRepository.findById(userId).orElseThrow(()
                -> new EntityNotFoundException("User", userId)));
    }

    @Override
    public List<UserContactsResponseDto> getUsersByIds(List<Long> userIds, Pageable page) {
        List<User> users;

        if (userIds == null || userIds.isEmpty()) {
            users = userRepository.findAll(page).toList();
        } else {
            users = userRepository.findAllByIdIn(userIds, page);
        }
        return  users.stream().map(userMapper::userToUserContactsResponseDto).collect(Collectors.toList());
    }
}
