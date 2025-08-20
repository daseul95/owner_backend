package me.dev.repository;

import me.dev.entity.Option;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option,Long> {
    Option findByName(String name);
    List<Option> findAllByIdIn(List<Long> optionId);
}
