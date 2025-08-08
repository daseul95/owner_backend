package me.dev.dto.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.dev.entity.Store;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreResponseDto {
    private Long id;
    private String storeName;
    private String ownerName;
    private Long userId;
    private String businessNum;
    private String postNum;
    private String description;
    private String phone;
    private String address;
    private Float lat;
    private Float longti;
    private String image;
    private Timestamp created_at;
    private Timestamp updated_at;

    // 필요한 필드만 받는 생성자 직접 추가
    public StoreResponseDto(String storeName,long userId, String businessNum,
                            String postNum,String description,String phone,
                            String address,float lat,float longti,String image) {
        this.storeName=storeName;
        this.userId = userId;
        this.businessNum = businessNum;
        this.postNum = postNum;
        this.description = description;
        this.phone = phone;
        this.address = address;
        this.lat = lat;
        this.longti = longti;
        this.image = image;
    }

    public StoreResponseDto(Store store) {
        this.id = store.getId();
        this.storeName = store.getStoreName();
        this.ownerName = store.getOwner().getName(); // User에 name 필드 있다고 가정
        this.businessNum = store.getBusinessNum();
        this.postNum = store.getPostNum();
        this.description = store.getDescription();
        this.phone = store.getPhone();
        this.address = store.getAddress();
        this.lat = store.getLat();
        this.longti = store.getLongti();
        this.image = store.getImage() != null ? store.getImage().toString() : null;
        this.created_at = store.getCreated_at();
        this.updated_at = store.getUpdated_at();
    }
}