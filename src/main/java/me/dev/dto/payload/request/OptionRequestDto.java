package me.dev.dto.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionRequestDto {
    private long id;
    private String name;
    private String category;
    private String des;
    private int price;
    private String imgUrl;
}
