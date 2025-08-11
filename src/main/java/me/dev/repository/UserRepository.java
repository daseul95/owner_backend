package me.dev.repository;

import me.dev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserId(String UserId);
    User findByUsername(String name);
    String findPasswordById(Long id);
    Boolean existsByUsername(String name);
    Boolean existsByEmail(String email);

}

