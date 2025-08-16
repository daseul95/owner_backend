package me.dev.controller;

import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.order.OrderDto;
import me.dev.dto.payload.order.OrderRequestDto;
import me.dev.entity.Order;
import me.dev.entity.enumerator.OrderStatus;
import me.dev.service.OrderService;
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
    @PostMapping("/order")
    @ResponseBody
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDto request) {
        Order order = orderService.createOrder(request);
        return ResponseEntity.ok(order);
    }

    // 주문 읽기
    @GetMapping("order/{id}")
    @ResponseBody
    public ResponseEntity<?> getOrder(@PathVariable("id")Long id){
        OrderDto orderdto = orderService.getOrder(id);
        return ResponseEntity.ok(orderdto);
    }

    // 주문 상태 변화 (PATCH)
    @PatchMapping("/order/status")
    public ResponseEntity<String> updateStatus(
            @PathVariable("id") Long id,
            @RequestParam OrderStatus status
    ) {
        orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok("주문 상태가 " + status + " 로 변경되었습니다.");
    }

    // 주문 수정 (PATCH)
    /*
    {
  "orderStatus": "IN_PROGRESS",
  "orderMenus": [
    { "menuId": 1, "quantity": 2 },
    { "menuId": 3, "quantity": 1 }
  ]
     */
    @PatchMapping("/order/{id}")
    @ResponseBody
    public ResponseEntity<?> updateOrder(
            @PathVariable("id")Long id,
            @RequestBody OrderRequestDto dto) {
        Order updatedOrder = orderService.updateOrder(id, dto);

        return ResponseEntity.ok(updatedOrder);
    }


}
