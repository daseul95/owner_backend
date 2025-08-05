package me.dev.dto.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderMenuRequestDto {
    private Long menuId;
    private int quantity;
    private List<SelectedOptionDto> selectedOptions;

    @Getter
    @Setter
    public static class SelectedOptionDto {
        private String optionName;
        private int optionPrice;
    }
}
