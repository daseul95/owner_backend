package me.dev.dto.payload.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import me.dev.entity.OrderMenu;
import me.dev.entity.Store;
import me.dev.entity.enumerator.OrderStatus;
import me.dev.entity.enumerator.OrderType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderDto {

    private Long id;
    private Long store;

    private int totalPrice;

    private List<OrderMenu> orderMenu = new ArrayList<>();

    private OrderStatus orderStatus = OrderStatus.WAITING;

}
