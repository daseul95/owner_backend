package me.dev.repository;

import me.dev.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store,Long> {
    Optional<Store> findFirstByUser_Id(Long id);
    List<Store> findAllByUser_Id(Long userId);

}
