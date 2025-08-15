package me.dev.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Table(name="option")
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Option {

    @Id
    @Column(name="option_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private String name;   // 옵션명 (예: "사이즈", "추가치즈")
    private int optionPrice;     // 옵션 가격
    private String des;
    private String imgUrl;


    @OneToMany(mappedBy = "option")
    @JsonManagedReference
    private List<OptionGroup> OptionGroup;

    public Option(String name, int optionPrice, String des,String imgUrl) {
        this.name = name;
        this.optionPrice = optionPrice;
        this.des = des;
        this.imgUrl = imgUrl;

    }




}