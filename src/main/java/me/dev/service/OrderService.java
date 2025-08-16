package me.dev.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.order.OrderMenuRequestDto;
import me.dev.dto.payload.order.OrderRequestDto;
import me.dev.entity.*;
import me.dev.entity.enumerator.OrderStatus;
import me.dev.repository.OptionRepository;
import me.dev.repository.MenuRepository;
import me.dev.repository.OrderRepository;
import me.dev.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final OptionRepository optionRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public Order createOrder(OrderRequestDto request) {
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(()-> new RuntimeException("해당 가게를 찾을 수 없습니다."));

        Order order =new Order();
        order.setStore(store);
        order.setOrderStatus(OrderStatus.WAITING);

        int total =0;
        for(OrderMenuRequestDto menuReq : request.getMenus()){
            OrderMenu menu = OrderMenu.builder()
                    .id(menuReq.getMenuId())
                    .quantity(menuReq.getQuantity())
                    .build();
            order.addOrderMenu(menu);
        }
        total = request.getTotalPrice();
        return orderRepository.save(order);
    }

    // 주문 상태 변경}
    public void updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));
        order.setOrderStatus(newStatus);
        orderRepository.save(order);
    }

}
