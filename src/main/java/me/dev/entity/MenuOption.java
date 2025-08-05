package me.dev.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name="menu_option")
@Setter
public class MenuOption {

    @Id
    @Column(name="menuOption_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;   // 옵션명 (예: "사이즈", "추가치즈")
    private int price;     // 옵션 가격

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

}