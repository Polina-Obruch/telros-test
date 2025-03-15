package ru.telros_test.dto.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;
import ru.telros_test.dto.UserContactsResponseDto;
import ru.telros_test.dto.UserProfileResponseDto;
import ru.telros_test.dto.UserRequestDto;
import ru.telros_test.dto.UserUpdateProfileDto;
import ru.telros_test.entity.User;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface UserMapper {

    User userRequestDtoToUser(UserRequestDto userRequestDto);

    UserContactsResponseDto userToUserContactsResponseDto (User user);

    User userUpdateProfileDtoToUser(UserUpdateProfileDto userUpdateProfileDto);

    UserProfileResponseDto userToUserProfileResponseDto (User user);
}
