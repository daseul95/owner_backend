package me.dev.dto.payload.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.dev.entity.MenuOptionGroup;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuOptionGroupDto {
    private Long id;
    private Long menuId;   // MenuDto 대신 ID만 넣기 권장
    private Long optionGroup;
    private boolean isRequired;


    public MenuOptionGroupDto(MenuOptionGroup entity) {
        this.id = entity.getId();
        this.menuId = entity.getMenu().getId();
        this.optionGroup = entity.getOptionGroup().getId();
        this.isRequired = entity.isRequired();
    }
}
