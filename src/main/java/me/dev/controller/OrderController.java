package me.dev.controller;

import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.order.OrderRequestDto;
import me.dev.dto.payload.response.OrderResponseDto;
import me.dev.entity.Order;
import me.dev.entity.enumerator.OrderStatus;
import me.dev.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    //오더 받기 (POST)
    /*
 {
  "storeId": 1,
  "menus": [
    { "menuId" : 1, "quantity": 2 },
    { "menuId" : 2, "quantity": 1 }
  ]
}
     */
    @PostMapping
    @ResponseBody
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDto request) {
        Order order = orderService.createOrder(request);
        return ResponseEntity.ok(order);
    }

    // 오더 상태 변화 (PATCH)
    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status
    ) {
        orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok("주문 상태가 " + status + " 로 변경되었습니다.");
    }


}
