package me.dev.repository;

import me.dev.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option,Long> {
    Option findByName(String name);
}
