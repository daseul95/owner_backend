package me.dev.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import me.dev.entity.enumerator.OrderStatus;
import me.dev.entity.enumerator.OrderType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.jndi.JndiLocatorDelegate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
@Setter
public class Order {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private String customer;
    private String customerPhone;

    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    private String delivery_address; // 배달일 경우만 사용

    // 주문한 메뉴 항목 여러 개 (1:N 관계)
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderMenu> orderMenu = new ArrayList<>();


    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.WAITING;

    private int totalPrice;
    private String payment_method;

    @CreationTimestamp
    private LocalDateTime create_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    public void addOrderMenu(OrderMenu menu) {
        this.orderMenu.add(menu);
        menu.setOrder(this);
    }

    public void updateMenus(List<OrderMenu> menus) {
        this.orderMenu.clear();        // 기존 메뉴들 다 삭제
        for (OrderMenu menu : menus) { // 새로 받은 메뉴들 하나씩 추가
            addOrderMenu(menu);        // 연관관계 편의 메서드 사용
        }
    }

}
