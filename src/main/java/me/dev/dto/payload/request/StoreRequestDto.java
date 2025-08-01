package me.dev.dto.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreRequestDto {
    private String storeName;
    private Long userId;  // User 객체 말고 ID만 받기
    private String businessNum;
    private String postNum;
    private String description;
    private String phone;
    private String address;
    private Float lat;
    private Float longti;
    private String image;  // URL은 String으로 받는 게 일반적
}