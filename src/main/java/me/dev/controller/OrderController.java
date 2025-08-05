package me.dev.controller;

import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.request.OrderRequest;
import me.dev.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /*
    {
              "customerId": "daseul95",
              "storeId": 1,
              "orderType": "DELIVERY",
              "deliveryAddress": "서울 강남구 역삼동 123-45",
              "orderItems": [
                {
                  "menuId": 1,
                  "quantity": 2,
                  "selectedMenuOptions": [1, 1]
                },
                {
                  "menuId": 1,
                  "quantity": 1,
                  "selectedMenuOptions": [1]
                }
              ]
            }
     */
    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        // 서비스에 위임하거나 직접 엔티티 생성
        orderService.createOrder(request);
        return ResponseEntity.ok().build();
    }
}
