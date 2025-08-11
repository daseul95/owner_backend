package me.dev.dto.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import me.dev.entity.enumerator.OrderType;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
        private Long id;
        private Long customerId;
        private List<OrderMenuRequestDto> orderMenus;
        private LocalDateTime created_at;
}
//
//        private String customer;
//        private String customerPhone;
//        private Long storeId;
//        private OrderType orderType;
//        private String deliveryAddress;
//        private String paymentMethod;

//}