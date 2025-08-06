package me.dev.service;

import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.request.MenuRequestDto;
import me.dev.dto.payload.request.ToastDto;
import me.dev.dto.payload.response.MenuOptionResponseDto;
import me.dev.dto.payload.response.MenuResponseDto;
import me.dev.entity.Menu;
import me.dev.entity.Store;
import me.dev.repository.MenuRepository;
import me.dev.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;

    public MenuResponseDto createMenu(Long storeId, MenuRequestDto dto) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 가게가 존재하지 않습니다."));

        Menu menu = new Menu();
        menu.setCategory(dto.getCategory());
        menu.setName(dto.getName());
        menu.setDes(dto.getDes());
        menu.setImgUrl(dto.getImgUrl());
        menu.setPrice(dto.getPrice());

        // 연관관계 설정
        menu.setStore(store);
        store.getMenus().add(menu); // 양방향일 경우

        Menu saved = menuRepository.save(menu);
        return new MenuResponseDto(saved);
    }

    public List<String> getMenuNames() {
        List<Menu> menus = menuRepository.findAll();
        return menus.stream()
                .map(Menu::getName)  // 메뉴 이름만 뽑기
                .collect(Collectors.toList());
    }
    public String getMenuNameById(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 없습니다."));
        return menu.getName();
    }

    public ToastDto getToastById(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("메뉴 없음"));

        return new ToastDto(
                menu.getId(),
                menu.getName(), // toastName에 대응
                menu.getDes(),
                menu.getImgUrl()
        );
    }

}