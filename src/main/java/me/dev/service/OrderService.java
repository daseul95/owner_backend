package me.dev.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.request.OrderMenuRequestDto;
import me.dev.dto.payload.request.OrderRequestDto;
import me.dev.entity.*;
import me.dev.entity.enumerator.OrderStatus;
import me.dev.repository.OptionRepository;
import me.dev.repository.MenuRepository;
import me.dev.repository.OrderRepository;
import me.dev.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final OptionRepository optionRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public Order createOrder(OrderRequestDto request) {

        // 1. 가게 확인
//        Store store = storeRepository.findById(request.getStoreId())
//                .orElseThrow(() -> new IllegalArgumentException("해당 가게 없음"));

        // 2. 주문 엔티티 생성
        Order order = new Order();
//        order.setStore(store);
//        order.setCustomer(request.getCustomer());
//        order.setOrderType(request.getOrderType());
        order.setOrderStatus(OrderStatus.WAITING);
        order.setCreate_at(LocalDateTime.now());

        List<OrderMenu> orderMenus = new ArrayList<>();
        int totalPrice = 0;


        // 3. 주문 아이템 처리
        for (OrderMenuRequestDto  itemRequest : request.getOrderMenus()) {
            Menu menu = menuRepository.findById(itemRequest.getMenuId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 메뉴 없음"));

            OrderMenu orderMenu = new OrderMenu();
            orderMenu.setOrder(order); // 연관관계 주입
            orderMenu.setMenu(menu);



            // 옵션 리스트 처리
            List<SelectedOption> selectedOptions = new ArrayList<>();
            for (OrderMenuRequestDto.SelectedOptionDto optionDto : itemRequest.getSelectedOptions()) {
                String optionName = optionDto.getOptionName();  // 프론트에서 받은 옵션명

                Option option = optionRepository.findByName(optionName);
                if (option == null) {
                    throw new IllegalArgumentException("해당 옵션이 존재하지 않습니다: " + optionName);
                }
                SelectedOption selectedOption = new SelectedOption();
                selectedOption.setOption(option);
                selectedOption.setOptionPrice(option.getOptionPrice());  // 옵션 가격 세팅
                selectedOption.setOrderMenu(orderMenu); // 연관관계 설정

                selectedOptions.add(selectedOption);
            }

            orderMenu.setSelectedOptions(selectedOptions);

            orderMenu.setQuantity(itemRequest.getQuantity());
            int optionTotalPrice = selectedOptions.stream()
                    .mapToInt(SelectedOption::getOptionPrice)
                    .sum();

            int menuTotalPrice = (menu.getPrice() + optionTotalPrice) * itemRequest.getQuantity();
            orderMenu.setTotalPrice(menuTotalPrice); // 총 가격 계산

            orderMenus.add(orderMenu);
            totalPrice += menuTotalPrice;
        }
        order.setOrderMenus(orderMenus);
        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);
//            // 4. 옵션 처리 (주문 메뉴에 딸린 옵션)
//
//            List<SelectedMenuOption> selectedOptions = new ArrayList<>();
//            for (Long optionId : itemRequest.getSelectedMenuOptions()) {
//                MenuOption menuOption = menuOptionRepository.findById(optionId)
//                        .orElseThrow(() -> new IllegalArgumentException("옵션 없음"));
//
//                SelectedMenuOption selectedOption = new SelectedMenuOption();
//                selectedOption.setOrderMenu(orderMenu);
//                selectedOption.setMenuOption(menuOption);
//                selectedOption.setOptionPrice(menuOption.getPrice());
//
//                selectedOptions.add(selectedOption);
//                menuTotalPrice += menuOption.getPrice();
//            }
//
//            orderMenu.setSelectedMenuOptions(selectedOptions);
//        // 5. 주문 저장 (Cascade 설정 필요)
//        orderRepository.save(order);
    }
    }
