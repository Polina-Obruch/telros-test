package ru.telros_test.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.telros_test.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findAllByIdIn(List<Long> userIds, Pageable pageable);

}
