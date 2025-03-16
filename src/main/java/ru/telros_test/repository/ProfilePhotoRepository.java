package ru.telros_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.telros_test.entity.Image;

public interface ProfilePhotoRepository extends JpaRepository<Image, Long> {

    Image getByUserId(Long userId);

    void deleteByUserId(Long userId);
}
