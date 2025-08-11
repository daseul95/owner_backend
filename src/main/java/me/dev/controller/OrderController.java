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

    /*
{
  "customer": "김다슬",
  "customerPhone": "010-1234-5678",
  "storeId": 1,
  "orderType": "DELIVERY",
  "deliveryAddress": "서울시 강남구 어딘가",
  "paymentMethod": "CARD",
  "orderMenus": [
    {
      "menuId": 101,
      "quantity": 2,
      "selectedOptions": [
        {
          "optionName": "치즈 추가",
          "optionPrice": 500
        },
        {
          "optionName": "베이컨 추가",
          "optionPrice": 1000
        } ] },
    {
      "menuId": 102,
      "quantity": 1,
      "selectedOptions": [
        {
          "optionName": "곱빼기",
          "optionPrice": 1500
        } ] } ]  }
     */
    @PostMapping("/order")
    @ResponseBody
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto request) {

        Order order = orderService.createOrder(request);
        OrderResponseDto dto = new OrderResponseDto();
        dto.setCustomer(request.getCustomer());
        dto.setCustomerPhone(request.getCustomerPhone());
        dto.setStoreId(request.getStoreId());
        dto.setOrderType(request.getOrderType());
        dto.setDeliveryAddress(request.getDeliveryAddress());
        dto.setOrderMenus(request.getOrderMenus());
        dto.setPaymentMethod(request.getPaymentMethod());
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

}
