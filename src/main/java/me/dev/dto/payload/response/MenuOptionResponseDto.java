package me.dev.dto.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.dev.entity.MenuOption;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class MenuOptionResponseDto {
    private Long id;
    private String name;
    private int price;

    public MenuOptionResponseDto(MenuOption option) {
        this.id = option.getId();
        this.name = option.getName();
        this.price = option.getOptionPrice();
    }

}

