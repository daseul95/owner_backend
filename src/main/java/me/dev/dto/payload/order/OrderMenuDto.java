package me.dev.dto.payload.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderMenuDto {
    private Long menuId;
    private int quantity;

}
