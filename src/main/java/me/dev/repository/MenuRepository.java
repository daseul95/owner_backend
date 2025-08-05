package me.dev.repository;

import me.dev.entity.Menu;
import me.dev.entity.MenuOption;
import me.dev.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu,Long> {
}
