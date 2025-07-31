package me.dev.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.net.URL;
import java.sql.Timestamp;

@Entity
@Table(name="store")
public class Store {

    @Id
    @Column(name="store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    private String storeName;
    private User ownerId;
    private String businessNum;
    private String postNum;
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
