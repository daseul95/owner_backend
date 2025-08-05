package me.dev.dto.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import me.dev.entity.enumerator.OrderType;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private String customerId;
    private Long storeId;

    @NotNull
    private OrderType orderType;

    private String deliveryAddress;

    private List<OrderMenuRequest> orderItems;

    @Getter @Setter
    public static class OrderMenuRequest {
        private Long menuId;
        private int quantity;
        private List<Long> selectedMenuOptions; // 선택된 옵션 ID들
    }
}