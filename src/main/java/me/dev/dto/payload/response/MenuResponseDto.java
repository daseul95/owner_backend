package me.dev.dto.payload.response;

import lombok.Getter;
import lombok.Setter;
import me.dev.entity.Menu;

import java.sql.Timestamp;


@Getter
@Setter
public class MenuResponseDto {

    private Long menuId;
    private String category;
    private String name;
    private String des;
    private String imgUrl;
    private int price;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Optional: 가게 이름도 포함 가능
    private String storeName;

    public MenuResponseDto(Long menuId,String category,String name,String des,String imgUrl,
                           int price) {
        this.menuId = menuId;
        this.category = category;
        this.name = name;
        this.des = des;
        this.imgUrl = imgUrl;
        this.price = price;
    }
}
