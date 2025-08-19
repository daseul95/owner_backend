package me.dev.repository;

import me.dev.entity.Store;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store,Long> {
    Optional<Store> findFirstByUser_Id(Long userId);
    List<Store> findAllByUser_Id(Long userId);

}
