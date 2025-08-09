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
    private String category;
    private String name;
    private String des;
    private String imgUrl;
    private int price;

    private Long storeId;
    private String storeName;

    private List<MenuOptionGroupDto> menuOptionGroups;

    // Menu 엔티티를 받아서 DTO로 변환하는 생성자
    public MenuDto(Menu menu) {
        this.id = menu.getId();
        this.category = menu.getCategory();
        this.name = menu.getName();
        this.des = menu.getDes();
        this.imgUrl = menu.getImgUrl();
        this.price = menu.getPrice();

        if (menu.getStore() != null) {
            this.storeId = menu.getStore().getId();
            this.storeName = menu.getStore().getStoreName();
        }

        if (menu.getMenuOptionGroups() != null) {
            this.menuOptionGroups = menu.getMenuOptionGroups().stream()
                    .map(MenuOptionGroupDto::new)
                    .collect(Collectors.toList());
        }
    }
}

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class MenuDto {
//    private Long id;
//    private String category;
//    private String name;
//    private String des;
//    private String imgUrl;
//    private int price;
//
//    // Store 정보가 필요하면 일부만 담거나 StoreDto를 따로 만들어서 넣기
//    private Long storeId;
//    private String storeName;
//
//    // MenuOptionGroup 도 DTO 리스트로 변환해서 담기
//    @JsonIgnore
//    private List<MenuOptionGroupDto> menuOptionGroups;
//
//    // 생성자 (엔티티 -> DTO 변환용)
//    public MenuDto(Menu menu) {
//        this.id = menu.getId();
//        this.category = menu.getCategory();
//        this.name = menu.getName();
//        this.des = menu.getDes();
//        this.imgUrl = menu.getImgUrl();
//        this.price = menu.getPrice();
//
//        if (menu.getStore() != null) {
//            this.storeId = menu.getStore().getId();
//            this.storeName = menu.getStore().getStoreName(); // 가정
//        }
//
//        if (menu.getMenuOptionGroups() != null) {
//            this.menuOptionGroups = menu.getMenuOptionGroups().stream()
//                    .map(MenuOptionGroupDto::new)  // MenuOptionGroupDto도 엔티티 생성자 필요
//                    .collect(Collectors.toList());
//        }
//    }
//
//}
