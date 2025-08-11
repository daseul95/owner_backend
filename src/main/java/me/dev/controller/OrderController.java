package me.dev.controller;

import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.request.OrderRequestDto;
import me.dev.dto.payload.response.OrderResponseDto;
import me.dev.entity.Order;
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
   "id":1,
  "customerId": 1001,
  "orderMenus": [
    {
      "menuId": 1,
      "quantity": 2,
      "selectedOptions": [
        {
          "optionName": "치즈 추가",
          "optionPrice": 1000
        },
        {
          "optionName": "베이컨 추가",
          "optionPrice": 1500
        }
      ]
    },
    {
      "menuId": 2,
      "quantity": 1,
      "selectedOptions": [
        {
          "optionName": "버섯 추가",
          "optionPrice": 1200
        }
      ]
    }
  ],
  "created_at": "2025-08-11T15:30:00"
}

     */
    @PostMapping("/order")
    @ResponseBody
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto request) {

        Order order = orderService.createOrder(request);

        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(request.getId());
        dto.setCustomerId(request.getCustomerId());
        dto.setOrderMenus(request.getOrderMenus());
        dto.setCreatedAt(request.getCreated_at());
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

}
