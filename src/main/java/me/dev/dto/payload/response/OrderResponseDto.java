package me.dev.dto.payload.response;

import lombok.Getter;
import lombok.Setter;
import me.dev.dto.payload.order.OrderMenuRequestDto;
import me.dev.entity.enumerator.OrderStatus;
import me.dev.entity.enumerator.OrderType;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {

    private Long id;
    private Long customerId;
    private List<OrderMenuRequestDto> orderMenus;
    private LocalDateTime createdAt;

    private String customer;
    private String customerPhone;
    private String storeName;
    private Long storeId;
    private OrderType orderType;
    private OrderStatus orderStatus;
    private int totalPrice;
    private String deliveryAddress;
    private String paymentMethod;

}
