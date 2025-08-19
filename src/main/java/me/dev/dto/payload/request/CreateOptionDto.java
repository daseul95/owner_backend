package me.dev.dto.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOptionDto {


    private String category;
    private String name;
    private int price;
    private String des;
    private String imgUrl;

}
