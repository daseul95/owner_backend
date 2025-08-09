//package me.dev.dto.payload.response;
//
//import com.google.errorprone.annotations.NoAllocation;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import me.dev.dto.payload.DTO.MenuDto;
//import me.dev.dto.payload.DTO.OptionGroupDto;
//import me.dev.entity.Menu;
//import me.dev.entity.MenuOptionGroup;
//import me.dev.entity.OptionGroup;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class MenuOptionGroupDto {
//
//    private long id;
//    private MenuDto menu;
//    private OptionGroupDto optionGroup;
//
//    public MenuOptionGroupDto(MenuOptionGroup entity) {
//        this.id = entity.getId();
//        this.menu = new MenuDto(entity.getMenu());            // DTO로 변환
//        this.optionGroup = new OptionGroupDto(entity.getOptionGroup()); // DTO로 변환
//    }
//}
//
