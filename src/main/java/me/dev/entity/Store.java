package me.dev.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.net.URL;
import java.sql.Timestamp;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Table(name="store")
public class Store {

    @Id
    @Column(name="store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    private String storeName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;
    private String businessNum;
    private String postNum;
    private String description;
    private String phone;
    private String address;
    private Float lat;
    private Float longti;
    @Nullable
    private String image;
    private Timestamp created_at;
    private Timestamp updated_at;

}
