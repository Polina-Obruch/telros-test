package ru.telros_test.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Size(min = 2, max = 200)
    private String name;

    @NotBlank(message = "Фамилия пользователя не может быть пустым")
    @Size(min = 2, max = 200)
    private String lastname;

    @Email(message = "Введите правильный email")
    @NotBlank(message = "Email не может быть пустым")
    @Size(min = 6, max = 254)
    private String email;

    @NotBlank(message = "Телефон не может быть пустым")
    @Size(min = 6, max = 20)
    private String phone;
}
