package me.dev.repository;

import me.dev.entity.MenuOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.OptionalDouble;

public interface MenuOptionRepository extends JpaRepository<MenuOption,Long> {
    MenuOption findByName(String name);
}
