package me.dev.dto.payload.response;

import lombok.Getter;
import lombok.Setter;
import me.dev.entity.Store;

import java.sql.Timestamp;

@Getter
@Setter
public class StoreResponseDto {
    private Long id;
    private String storeName;
    private String ownerName;
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

    public StoreResponseDto(Store store) {
        this.id = store.getStoreId();
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