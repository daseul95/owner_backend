package me.dev.dto.payload.order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
        private Long storeId;
        private List<OrderMenuDto> menus;
        private int totalPrice;
}
//
//        private String customer;
//        private String customerPhone;
//        private Long storeId;
//        private OrderType orderType;
//        private String deliveryAddress;
//        private String paymentMethod;

//}