package ru.telros_test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserContactsResponseDto {
    private Long id;
    private String name;
    private String lastname;
    private String email;
    private String phone;
}
