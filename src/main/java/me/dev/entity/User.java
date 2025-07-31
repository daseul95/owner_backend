package me.dev.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name="user")
@Getter
@Setter
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;

    private String ceo_name;

    private String area;

    private String note;

    @Column(unique = true)
    private String email;

    private String password;

    private String phone;

    private Timestamp created_at;

}
