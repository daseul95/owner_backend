package me.dev.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.errorprone.annotations.NoAllocation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.dev.dto.payload.DTO.OptionGroupDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="menu")
@Getter
@Setter
public class Menu {


    @Id
    @Column(name="menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private User user;

    private String category;
    private String name;
    private String des;
    private String imgUrl;
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    @JsonBackReference
    private Store store;

//    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<MenuOption> options = new ArrayList<>();

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    @JsonIgnore
    @JsonManagedReference
    private List<MenuOptionGroup> menuOptionGroups = new ArrayList<>();

}

//    // 옵션 추가 편의 메서드
//    public void addOption(MenuOptionGroup group) {
//        menuOptionGroups.add(group);
//        group.setMenu(this);
//    }
//
//    // 옵션 제거 편의 메서드 (필요하면)
//    public void removeOption(MenuOptionGroup group) {
//        menuOptionGroups.remove(group);
//        group.setMenu(null);
//    }


//    @CreationTimestamp
//    @Column(updatable = false)
//    private Timestamp created_at;
//
//    @UpdateTimestamp
//    private Timestamp updated_at;

    // === 편의 메서드 ===
//    public void setStore(Store store) {
//        this.store = store;
//        if (!store.getMenus().contains(this)) {
//            store.getMenus().add(this);
//        }
//    }


