package me.dev.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.net.URL;
import java.sql.Timestamp;

@Entity
@Table(name="store")
public class Store {

    @Id
    @Column(name="sotre_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long store_id;

    private String store_name;
    private User owner_id;
    private String business_num;
    private String post_num;
    private String description;
    private String phone;
    private String address;
    private Float lat;
    private Float longti;
    @Nullable
    private URL image;
    private Timestamp created_at;
    private Timestamp updated_at;

}
