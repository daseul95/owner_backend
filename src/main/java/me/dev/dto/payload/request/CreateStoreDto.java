package me.dev.dto.payload.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreateStoreDto {
    private String storeName;
    private String businessNum;
    private String postNum;
    private String userId;
    private String description;
    private String phone;
    private String address;
    private Float lat;
    private Float longti;
    private String imgUrl;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}
