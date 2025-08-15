package me.dev.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "option_by_group")
@Getter
@Setter
public class OptionByGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groups_id", nullable = false)
    @JsonBackReference
    private Group group;

    @OneToMany(mappedBy = "OptionByGroup", cascade = CascadeType.ALL)
    private List<Option> options;

    private int displayOrder;      // 옵션 순서 관리용

    // getters, setters, constructors
}