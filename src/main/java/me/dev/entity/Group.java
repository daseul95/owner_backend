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
    @Column(name="id")
    private Long id;

    private String name;
    private String des;

    @OneToMany(mappedBy = "group")
    @JsonManagedReference
    private List<MenuGroup> MenuGroup;

    @OneToMany(mappedBy = "group")
    @JsonManagedReference
    private List<OptionByGroup> OptionByGroup;

}
