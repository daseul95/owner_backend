package me.dev.dto.payload.order;

import lombok.Getter;
import lombok.Setter;
import me.dev.entity.enumerator.OrderStatus;

import java.util.List;

@Getter
@Setter
public class OrderResponseDto {

    private Long id;
    private Long customerId;
    private Long storeId;
    private List<OrderMenuDto> orderMenus;
    private OrderStatus orderStatus;

}
