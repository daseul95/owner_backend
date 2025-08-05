package me.dev.service;

import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.request.MenuOptionRequestDto;
import me.dev.dto.payload.response.MenuOptionResponseDto;
import me.dev.entity.Menu;
import me.dev.entity.MenuOption;
import me.dev.repository.MenuOptionRepository;
import me.dev.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuOptionService {

    private final MenuRepository menuRepository;
    private final MenuOptionRepository menuOptionRepository;

    public MenuOptionResponseDto createMenuOption(Long menuId, MenuOptionRequestDto dto) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 없습니다."));

        MenuOption option = new MenuOption();
        option.setName(dto.getName());
        option.setOptionPrice(dto.getOptionPrice());

        menu.addOption(option);

        MenuOption saved = menuOptionRepository.save(option);
        return new MenuOptionResponseDto(saved);
    }
    public List<MenuOptionResponseDto> getMenuById(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 없습니다."));

        return menu.getOptions().stream()
                .map(MenuOptionResponseDto::new)
                .collect(Collectors.toList());
    }
}