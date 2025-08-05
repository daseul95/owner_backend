package me.dev.dto.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuRequestDto {

    private Long storeId;

    private String category;
    private String name;
    private String des;
    private String imgUrl;
    private int price;


}