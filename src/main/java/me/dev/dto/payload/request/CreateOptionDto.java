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


    private String name;
    private int optionPrice;
    private String des;
    private String imgUrl;

}
