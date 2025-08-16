package me.dev.dto.payload.order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderMenuRequestDto {
    private Long menuId;
    private int quantity;

}
