package me.dev.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.dev.dto.payload.DTO.MenuDto;
import me.dev.dto.payload.DTO.OptionGroupDto;
import me.dev.repository.MenuRepository;
import me.dev.repository.OptionGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;



@Getter
@Setter
@Entity
@Table(name = "menu_option_groups")
@NoArgsConstructor
@AllArgsConstructor
public class MenuOptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    @JsonBackReference
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "option_group_id")
    @JsonBackReference
    private OptionGroup optionGroup;

    public MenuOptionGroup(OptionGroup optionGroup){
        this.optionGroup = optionGroup;
    }

    private boolean isRequired;


    public MenuOptionGroup(Menu menu, OptionGroup group) {
        this.menu=menu;
        this.optionGroup=group;
    }
}
