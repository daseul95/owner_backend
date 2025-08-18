package me.dev.repository;

import me.dev.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group,Long> {
    List<Group> findAllByIdIn(List<Long> groupId);
}
