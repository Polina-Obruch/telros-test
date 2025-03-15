package ru.telros_test.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@Builder
@ToString
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    private String patronymic;
    private LocalDate birthdate;

}
