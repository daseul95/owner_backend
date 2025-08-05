package me.dev.dto.payload.response;

import lombok.Getter;
import me.dev.entity.Menu;

import java.sql.Timestamp;


@Getter
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

    public MenuResponseDto(Menu menu) {
        this.menuId = menu.getId();
        this.category = menu.getCategory();
        this.name = menu.getName();
        this.des = menu.getDes();
        this.imgUrl = menu.getImgUrl();
        this.price = menu.getPrice();
        this.createdAt = menu.getCreated_at();
        this.updatedAt = menu.getUpdated_at();
        this.storeName = menu.getStore().getStoreName(); // NPE 주의
    }
}
