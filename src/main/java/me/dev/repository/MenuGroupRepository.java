package me.dev.repository;


import me.dev.entity.Group;
import me.dev.entity.MenuGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import me.dev.entity.Menu;
public interface MenuGroupRepository extends JpaRepository<MenuGroup,Long> {
    boolean existsByMenuAndGroup(Menu menu, Group group);
}
