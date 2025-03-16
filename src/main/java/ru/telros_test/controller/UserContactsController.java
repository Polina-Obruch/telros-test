package ru.telros_test.controller;

import jakarta.validation.Valid;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.telros_test.core.utils.PaginationUtils;
import ru.telros_test.dto.UserContactsResponseDto;
import ru.telros_test.dto.UserRequestDto;
import ru.telros_test.service.UserContactsService;

import java.util.List;

//Контроллер для работы с обязательной (контактной информацией) - Имя, Фамилия, Телефон, Почта
@Slf4j
@Validated
@RestController
@RequestMapping("/admin/users")
public class UserContactsController {
    private final UserContactsService userContactsService;

    @Autowired
    public UserContactsController(UserContactsService userContactsService) {
        this.userContactsService = userContactsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserContactsResponseDto addUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        log.info("Запрос на добавление нового пользователя");
        return userContactsService.addUser(userRequestDto);
    }

    @GetMapping("/{userId}")
    public UserContactsResponseDto getUserById(@PathVariable Long userId) {
        log.info("Запрос на выдачу пользователя по ID");
        return userContactsService.getUserById(userId);
    }

    @GetMapping
    public List<UserContactsResponseDto> getAllUsers(@RequestParam(name = "ids", required = false) List<Long> userIds,
                                     @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        log.info("Запрос на выдачу пользователей");
        return userContactsService.getUsersByIds(
                userIds, PaginationUtils.toPage(page, size));
    }
}
