package me.dev.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "option_groups")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OptionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "optionGroup", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<MenuOption> options = new ArrayList<>();


    public void addOption(MenuOption option) {
        options.add(option);
        option.setOptionGroup(this);
    }



}
