package me.dev.repository;

import me.dev.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store,Long> {

    Optional<Store> findByOwner_Id(Long userId);
    List<Store> findAllByOwner_Id(Long userId);

}
