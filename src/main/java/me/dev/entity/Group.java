package me.dev.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

//    @OneToMany(mappedBy = "groups")
//    @JsonManagedReference
//    private List<MenuGroup> MenuGroup = new ArrayList<>();

    @OneToMany(mappedBy = "groups")
    @JsonManagedReference
    private List<OptionGroup> OptionGroup = new ArrayList<>();

}
