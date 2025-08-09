package me.dev.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.request.CreateMenuDTO;
import me.dev.dto.payload.request.MenuRequestDto;
import me.dev.dto.payload.response.MenuResponseDto;
import me.dev.entity.Menu;
import me.dev.entity.Store;
import me.dev.entity.User;
import me.dev.repository.MenuRepository;
import me.dev.repository.StoreRepository;
import me.dev.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final UserRepository userRepository;

    public Menu createMenu(Long storeId,Long userId, CreateMenuDTO dto) {
        Optional<Store> optionalStore = storeRepository.findById(storeId);
        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("유저를 찾을 수 없습니다"));
        // 1) 값이 없으면 예외 던지기
        Store shop = optionalStore.orElseThrow(() -> new RuntimeException("스토어가 없습니다"));
        // 2) 값이 있으면 처리, 없으면 다른 로직
        if(optionalStore.isPresent()) {
            Store store = optionalStore.get();
            // 처리
        } else {
            // 스토어 없음 처리
        }
        Store store = optionalStore.orElse(null);
        Menu menu = new Menu();
        menu.setUser(user);
        menu.setCategory(dto.getCategory());
        menu.setName(dto.getName());
        menu.setDes(dto.getDes());
        menu.setImgUrl(dto.getImgUrl());
        menu.setPrice(dto.getPrice());

        // 연관관계 설정
        menu.setStore(store);
        store.getMenus().add(menu); // 양방향일 경우

        Menu saved = menuRepository.save(menu);
        return menu;
    }

    @Transactional
    public void updateMenu(Long id, MenuRequestDto dto) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(("메뉴를 찾을수 없습니다")));

        if (dto.getTitle() != null) menu.setName(dto.getTitle());
        if (dto.getContents() != null) menu.setDes(dto.getContents());
        if (dto.getWriteTime() != null) menu.setUpdated_at(dto.getWriteTime());
    }

    public Menu getMenuByUserId(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 메뉴를 찾을 수 없습니다."));
        return menu;
    }


    public List<MenuResponseDto> getMenusByUserId(Long userId) {
        List<Menu> menus = menuRepository.findByStore_User_Id(userId);
        System.out.println("조회된 메뉴 개수 = {}"+ menus.size());
        return menus.stream()
                .map(menu -> {
                    MenuResponseDto dto = new MenuResponseDto(
                            menu.getId(),
                            menu.getCategory(),
                            menu.getName(),
                            menu.getDes(),
                            menu.getImgUrl(),
                            menu.getPrice());
        dto.setCreatedAt(menu.getCreated_at());
        dto.setUpdatedAt(menu.getUpdated_at());
        return dto;
                })
                .collect(Collectors.toList()
                );
    }

    public String getMenuNameById(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 없습니다."));
        return menu.getName();
    }

    public MenuResponseDto getMenuById(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("메뉴 없음"));

        return new MenuResponseDto(
                menu.getId(),
                menu.getCategory(),
                menu.getName(),
                menu.getDes(),
                menu.getImgUrl(),
                menu.getPrice()
        );
    }

    public void deleteMenu(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("food not found with id: " + id));
        menuRepository.delete(menu);

    }
}