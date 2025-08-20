package me.dev.repository;

import me.dev.entity.Group;
import me.dev.entity.Option;
import me.dev.entity.OptionByGroup;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OptionByGroupRepository extends JpaRepository<OptionByGroup,Long> {

    @Modifying
    @Transactional
    @Query("UPDATE OptionByGroup obg SET obg.group = :newGroup WHERE obg.group = :oldGroup")
    int updateGroup(@Param("oldGroup") Group oldGroup,
                    @Param("newGroup") Group newGroup);

    @Modifying
    @Transactional
    @Query("DELETE FROM OptionByGroup obg WHERE obg.group = :group")
    int deleteByGroup(@Param("group") Group group);


    @Query("select obg.option.id from OptionByGroup obg where obg.group.id = :groupId")
    List<OptionByGroup> findByGroupId(@Param("groupId") Long groupId);
}

