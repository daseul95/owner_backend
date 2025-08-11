package me.dev.dto.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateStoreDto {
    private String storeName;
    private String businessNum;
    private String postNum;
    private String description;
    private String phone;
    private String address;
    private Float lat;
    private Float longti;
    private String image;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}
