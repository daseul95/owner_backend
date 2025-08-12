package me.dev.dto.payload.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.dev.entity.Menu;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuDto {

    private Long id;
    private String name;
    private String des;
    private String imgUrl;
    private int price;

    private Long storeId;
    private String storeName;

    private List<MenuGroupDto> menuGroups;

    // Menu 엔티티를 받아서 DTO로 변환하는 생성자
    public MenuDto(Menu menu) {
        this.id = menu.getId();
        this.name = menu.getName();
        this.des = menu.getDes();
        this.imgUrl = menu.getImgUrl();
        this.price = menu.getPrice();

        if (menu.getStore() != null) {
            this.storeId = menu.getStore().getId();
            this.storeName = menu.getStore().getStoreName();
        }

        if (menu.getMenuGroups() != null) {
            this.menuGroups = menu.getMenuGroups().stream()
                    .map(MenuGroupDto::new)
                    .collect(Collectors.toList());
        }
    }
}

