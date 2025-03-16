package ru.telros_test.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "images")
public class Image {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "file_name")
    private String fileName;
    @Column(name = "mime_type")
    private String mimeType;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private byte[] data;
}
