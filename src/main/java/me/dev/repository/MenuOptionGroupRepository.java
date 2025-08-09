package me.dev.repository;

import me.dev.entity.MenuOptionGroup;
import me.dev.entity.OptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import me.dev.entity.Menu;
public interface MenuOptionGroupRepository extends JpaRepository<MenuOptionGroup,Long> {
    boolean existsByMenuAndOptionGroup(Menu menu, OptionGroup optionGroup);
}
