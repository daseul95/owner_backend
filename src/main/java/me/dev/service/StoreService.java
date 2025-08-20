package me.dev.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.request.CreateStoreDto;
import me.dev.dto.payload.response.StoreResponseDto;
import me.dev.repository.StoreRepository;
import me.dev.repository.UserRepository;
import me.dev.entity.Store;
import me.dev.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    @Autowired
    private S3Service s3Service;

    @Autowired
    private  S3Uploader s3Uploader;


    /*
    createStoreDto {
    String storeName;
    String businessNum;
    String postNum;
    String description;
    String phone;
    String address;
    Float lat;
    Float longti;
    String imgUrl;
   }
     */
    public CreateStoreDto createStore(CreateStoreDto dto,User user) throws IOException {
        System.out.println("userDetail.getId(): "+user.getId());


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
                .imgUrl(dto.getImgUrl())
                .build();

        storeRepository.save(store);
        dto.setCreated_at(store.getCreatedAt());
        dto.setUpdated_at(store.getUpdatedAt());
        return dto;
    }

    /*
      createStoreDto {
    String storeName;
    String businessNum;
    String postNum;
    String description;
    String phone;
    String address;
    Float lat;
    Float longti;
    String imgUrl;
   }
     */
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

    public Long getStoreIdByUser_Id(Long id){
        Optional<Store> storeOptional = storeRepository.findFirstByUser_Id(id);
        if (storeOptional.isPresent()) {
            Store store = storeOptional.get();
            Long storeId = store.getId();
            return storeId;
        }else {
            throw new RuntimeException("해당 유저의 스토어가 없습니다.");
        }
    }

    public List<Store> getStoresByUser_Id(Long userId) {
        return storeRepository.findAllByUser_Id(userId);
    }

    public void deleteStore(Long id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Store not found with id: " + id));
        storeRepository.delete(store);
    }



}
