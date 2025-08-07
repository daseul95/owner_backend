package me.dev.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
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
    private String category;
    private String name;
    private String des;
    private String imgUrl;
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuOption> options = new ArrayList<>();

    // 옵션 추가 편의 메서드
    public void addOption(MenuOption option) {
        options.add(option);
        option.setMenu(this);
    }

    // 옵션 제거 편의 메서드 (필요하면)
    public void removeOption(MenuOption option) {
        options.remove(option);
        option.setMenu(null);
    }


    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp created_at;

    @UpdateTimestamp
    private Timestamp updated_at;

    // === 편의 메서드 ===
    public void setStore(Store store) {
        this.store = store;
        if (!store.getMenus().contains(this)) {
            store.getMenus().add(this);
        }
    }

}
