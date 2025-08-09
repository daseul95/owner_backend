package me.dev.dto.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CreateMenuDTO {
    private Long storeId;
    private String name;
    private String category;
    private String title;
    private String contents;
    private String des;
    private Timestamp writeTime;
    private String imgUrl;
    private int price;

}
