package me.dev.dto.payload.response;

import lombok.Getter;
import lombok.Setter;
import me.dev.dto.payload.request.OrderMenuRequestDto;
import me.dev.entity.enumerator.OrderStatus;
import me.dev.entity.enumerator.OrderType;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private Long orderId;
    private String customer;
    private String customerPhone;
    private String storeName;
    private Long storeId;
    private OrderType orderType;
    private OrderStatus orderStatus;
    private int totalPrice;
    private String deliveryAddress;
    private List<OrderMenuRequestDto> orderMenus;
    private String paymentMethod;

    private Timestamp createdAt;
}
