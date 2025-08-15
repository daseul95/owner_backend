//package me.dev.dto.payload.response;
//
//import com.google.errorprone.annotations.NoAllocation;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import me.dev.dto.payload.DTO.MenuDto;
//import me.dev.dto.payload.DTO.OptionByGroupDto;
//import me.dev.entity.Menu;
//import me.dev.entity.MenuOptionByGroup;
//import me.dev.entity.OptionByGroup;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class MenuOptionByGroupDto {
//
//    private long id;
//    private MenuDto menu;
//    private OptionByGroupDto OptionByGroup;
//
//    public MenuOptionByGroupDto(MenuOptionByGroup entity) {
//        this.id = entity.getId();
//        this.menu = new MenuDto(entity.getMenu());            // DTO로 변환
//        this.OptionByGroup = new OptionByGroupDto(entity.getOptionByGroup()); // DTO로 변환
//    }
//}
//
