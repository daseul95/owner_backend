package me.dev.dto.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuOptionRequestDto {
    private String name;
    private String category;
    private String des;
    private int optionPrice;
}
