package me.dev.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.order.OrderDto;
import me.dev.dto.payload.order.OrderMenuDto;
import me.dev.dto.payload.order.OrderRequestDto;
import me.dev.dto.payload.order.OrderResponseDto;
import me.dev.entity.*;
import me.dev.entity.enumerator.OrderStatus;
import me.dev.repository.OptionRepository;
import me.dev.repository.MenuRepository;
import me.dev.repository.OrderRepository;
import me.dev.repository.StoreRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final OptionRepository optionRepository;
    private final OrderRepository orderRepository;

    //주문생성
    @Transactional
    public Order createOrder(OrderRequestDto request) {
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(()-> new RuntimeException("해당 가게를 찾을 수 없습니다."));

        Order order =new Order();
        order.setStore(store);
        order.setOrderStatus(OrderStatus.WAITING);

        int total =0;
        for(OrderMenuDto menuReq : request.getMenus()){
            OrderMenu menu = OrderMenu.builder()
                    .id(menuReq.getMenuId())
                    .quantity(menuReq.getQuantity())
                    .build();
            order.addOrderMenu(menu);
        }
        total = request.getTotalPrice();
        return orderRepository.save(order);
    }

    // 주문 상태 변경
    public void updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));
        order.setOrderStatus(newStatus);
        orderRepository.save(order);
    }

    // 주문 조회
    public OrderDto getOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new IllegalArgumentException("주문을 찾을수 없습니다."));
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setStore(order.getStore().getId());
        dto.setOrderMenu(order.getOrderMenu());
        dto.setOrderStatus(order.getOrderStatus());

        return dto;
        }

        //주문 수정
        public Order updateOrder(Long id,OrderRequestDto dto){
            Order order = orderRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Order not found"));

            List<OrderMenu> newMenus = new ArrayList<>();
            for (OrderMenuDto menudDto : dto.getMenus()){
                Menu menu = menuRepository.findById(menudDto.getMenuId())
                        .orElseThrow(()->new IllegalArgumentException("Menu not found"));

                OrderMenu orderMenu = OrderMenu.create(menu, menudDto.getQuantity());
                order.addOrderMenu(orderMenu);

                newMenus.add(orderMenu);
            }
            order.updateMenus(newMenus);
            return order;
        }


}

