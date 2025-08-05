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
              "storeId": 1,
              "customerId": 1,
              "orderType": "DELIVERY",
              "orderItems": [
                {
                  "menuId": 1,
                  "quantity": 2,
                  "selectedOptions": [
                    { "optionName": "햄추가"
                    ,"optionPrice" : 1000},
                    { "optionName": "햄추가"
                     ,"optionPrice" : 1000}
                  ]
                },
                {
                  "menuId": 2,
                  "quantity": 1,
                  "selectedOptions": [
                    { "optionName": "햄추가"
                     ,"optionPrice" : 1000}
                  ]
                }
              ]
            }
     */
    @PostMapping("/order")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest request) {
        try {
            orderService.createOrder(request);
            return ResponseEntity.ok("주문이 성공적으로 생성되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("주문 생성 실패: " + e.getMessage());
        }
    }
}
