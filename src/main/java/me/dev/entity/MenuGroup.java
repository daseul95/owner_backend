package me.dev.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "menu_groups")
@NoArgsConstructor
@AllArgsConstructor
public class MenuGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    @JsonBackReference
    private Menu menu;




    @ManyToOne
    @JoinColumn(name = "groups_id")
    private Group group;

    private boolean isRequired;


    public MenuGroup(Menu menu, Group group) {
        this.menu=menu;
        this.group=group;
    }
}
