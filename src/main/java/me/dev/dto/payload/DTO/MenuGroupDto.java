package me.dev.dto.payload.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.dev.entity.MenuGroup;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuGroupDto {
    private Long id;
    private Long menuId;   // MenuDto 대신 ID만 넣기 권장
    private Long group;
    private boolean isRequired;


    public MenuGroupDto(MenuGroup entity) {
        this.id = entity.getId();
        this.menuId = entity.getMenu().getId();
        this.group = entity.getGroup().getId();
        this.isRequired = entity.isRequired();
    }
}
