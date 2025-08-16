package me.dev.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@Table(name="order_menu")
public class OrderMenu {


    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;


    private int quantity;

    private int totalPrice;

    public static OrderMenu create(Menu menu, int quantity) {
        return OrderMenu.builder()
                .menu(menu)
                .quantity(quantity)
                .id(menu.getId())
                .build();
    }




}
