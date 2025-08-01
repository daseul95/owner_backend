package me.dev.entity;

import jakarta.persistence.*;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="menu")
public class Menu {

    @Id
    @Column(name="menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    private String category;
    private String name;
    private String des;
    private String imgUrl;
    private int price;
    private Timestamp create_at;



}
