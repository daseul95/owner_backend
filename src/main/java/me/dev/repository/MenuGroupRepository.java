package me.dev.repository;


import me.dev.entity.Group;
import me.dev.entity.MenuGroup;
import me.dev.entity.OptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import me.dev.entity.Menu;
public interface MenuGroupRepository extends JpaRepository<MenuGroup,Long> {
    boolean existsByMenuAndOptionGroup(Menu menu, OptionGroup optionGroup);
}
