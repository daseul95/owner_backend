package me.dev.dto.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMenuOptionDto {

    private Long optionGroup;
    private String optionGroupName;
    private String name;
    private String category;
    private int optionPrice;
    private String des;

}
