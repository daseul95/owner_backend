package me.dev.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Table(name="menu_option")
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuOption {

    @Id
    @Column(name="menuOption_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;   // 옵션명 (예: "사이즈", "추가치즈")
    private String category;
    private int optionPrice;     // 옵션 가격
    private String des;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_group_id")
    @JsonBackReference
    @JsonIgnore
    private OptionGroup optionGroup;

    public MenuOption(String name, String category, int optionPrice, String des, Menu menu, OptionGroup optionGroup) {
        this.name = name;
        this.category = category;
        this.optionPrice = optionPrice;
        this.des = des;
        this.optionGroup = optionGroup;
    }



}