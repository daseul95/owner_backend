package me.dev.dto.payload.response;

import lombok.Getter;
import me.dev.entity.MenuOption;

@Getter
public class MenuOptionResponseDto {
    private Long id;
    private String name;
    private int price;

    public MenuOptionResponseDto(MenuOption option) {
        this.id = option.getId();
        this.name = option.getName();
        this.price = option.getPrice();
    }

}

