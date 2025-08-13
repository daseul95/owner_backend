package me.dev.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.request.CreateStoreDto;
import me.dev.dto.payload.response.StoreResponseDto;
import me.dev.repository.StoreRepository;
import me.dev.repository.UserRepository;
import me.dev.entity.Store;
import me.dev.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final S3Service s3Service;

    public void createStore(CreateStoreDto dto,@AuthenticationPrincipal User userDetails
    ,String dirName,MultipartFile file) {



        User user= userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));


        System.out.println("create store 서비스 진입");

        Store store = Store.builder()
                .user(user)
                .storeName(dto.getStoreName())
                .businessNum(dto.getBusinessNum())
                .postNum(dto.getPostNum())
                .description(dto.getDescription())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .lat(dto.getLat())
                .longti(dto.getLongti())
                .imgUrl(s3Service.uploadFile(dirName,file))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        storeRepository.save(store);
    }

    @Transactional
    public void updateStore(Long id,CreateStoreDto dto){
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
        if(dto.getImgUrl()!=null)store.setImgUrl(dto.getImgUrl());

    }
    public StoreResponseDto getStoreById(Long id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 가게를 찾을 수 없습니다."));
        StoreResponseDto storeDto = new StoreResponseDto();
        storeDto.setCreatedAt(storeDto.getCreatedAt());
        storeDto.setUpdatedAt(storeDto.getUpdatedAt());
        return storeDto;
    }


    public Store getStoreByUserId(Long userId) {
        return storeRepository.findFirstByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Store not found for user id: " + userId));
    }

    public Long getStoreIdByUserId(Long userId){
        Optional<Store> storeOptional = storeRepository.findFirstByUser_Id(userId);
        if (storeOptional.isPresent()) {
            Store store = storeOptional.get();
            Long storeId = store.getId();
            return storeId;
        }else {
            throw new RuntimeException("해당 유저의 스토어가 없습니다.");
        }
    }

    public List<Store> getStoresByUserId(Long userId) {
        return storeRepository.findAllByUser_Id(userId);
    }

    public void deleteStore(Long id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Store not found with id: " + id));
        storeRepository.delete(store);
    }



}
