package me.dev.repository;

import me.dev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
    User findByCeoName(String name);
    Boolean existsByCeoName(String name);
    Boolean existsByEmail(String email);

}

