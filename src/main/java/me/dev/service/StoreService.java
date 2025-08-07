package me.dev.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.request.StoreRequestDto;
import me.dev.dto.payload.response.StoreResponseDto;
import me.dev.repository.StoreRepository;
import me.dev.repository.UserRepository;
import me.dev.entity.Store;
import me.dev.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    public void createStore(StoreRequestDto dto) {

        User owner= userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));


        System.out.println("create store 서비스 진입");

        Store store = Store.builder()
                .storeName(dto.getStoreName())
                .owner(owner)
                .businessNum(dto.getBusinessNum())
                .postNum(dto.getPostNum())
                .description(dto.getDescription())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .lat(dto.getLat())
                .longti(dto.getLongti())
                .image(dto.getImage())
                .created_at(Timestamp.valueOf(LocalDateTime.now()))
                .updated_at(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        storeRepository.save(store);
    }

    @Transactional
    public void updateStore(Long id,StoreRequestDto dto){
        Store store = storeRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(("가게를 찾을수 없습니다")));

        if(dto.getStoreName() !=null) store.setStoreName(dto.getStoreName());
        if(dto.getBusinessNum()!=null) store.setBusinessNum(dto.getBusinessNum());
        if(dto.getPostNum()!=null) store.setPostNum(dto.getPostNum());
        if(dto.getDescription()!=null) store.setDescription(dto.getDescription());
        if(dto.getPhone()!=null) store.setPhone(dto.getPhone());
        if(dto.getAddress()!=null) store.setAddress(dto.getAddress());
        if(dto.getLat()!=null) store.setLat(dto.getLat());
        if(dto.getLongti()!=null) store.setLongti(dto.getLongti());
        if(dto.getImage()!=null)store.setImage(dto.getImage());

    }
    public StoreResponseDto getStoreById(Long id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 가게를 찾을 수 없습니다."));
        return new StoreResponseDto(store);
    }

    public Store getStoreByUserId(Long userId) {
        return storeRepository.findByOwner_Id(userId)
                .orElseThrow(() -> new RuntimeException("Store not found for user id: " + userId));
    }

    public List<Store> getStoresByUserId(Long userId) {
        return storeRepository.findAllByOwner_Id(userId);
    }

    public void deleteStore(Long id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Store not found with id: " + id));
        storeRepository.delete(store);
    }



}
