package ru.telros_test.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateProfileDto{

    @Size(min = 2, max = 200)
    private String name;

    @Size(min = 2, max = 200)
    private String lastname;

    @Email(message = "Введите правильный email")
    @Size(min = 6, max = 254)
    private String email;

    @Size(min = 6, max = 20)
    private String phone;

    @Size(min = 2, max = 200)
    private String patronymic;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
}
