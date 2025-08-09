package me.dev.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.request.MenuOptionRequestDto;
import me.dev.dto.payload.response.MenuOptionResponseDto;
import me.dev.entity.Menu;
import me.dev.entity.MenuOption;
import me.dev.entity.MenuOptionGroup;
import me.dev.entity.OptionGroup;
import me.dev.repository.MenuOptionGroupRepository;
import me.dev.repository.MenuOptionRepository;
import me.dev.repository.MenuRepository;
import me.dev.repository.OptionGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuOptionService {

    @Autowired
    private  MenuRepository menuRepository;
    @Autowired
    private  OptionGroupRepository optionGroupRepository;
    @Autowired
    private MenuOptionGroupRepository menuOptionGroupRepository;
    @Autowired
    private  MenuOptionRepository optionRepository;

//    public MenuOptionResponseDto createMenuOption(Long menuId, MenuOptionRequestDto dto) {
//        Menu menu = menuRepository.findById(menuId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 없습니다."));
//
//        MenuOption option = new MenuOption();
//        option.setName(dto.getName());
//        option.setOptionPrice(dto.getOptionPrice());
//
//        menu.addOption(option);
//
//        MenuOption saved = menuOptionRepository.save(option);
//        return new MenuOptionResponseDto(saved);
//    }

    // 2. 메뉴에 옵션 그룹 연결
    public void connectMenuAndOptionGroup(Long menuId, Long optionGroupId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("메뉴 없음"));
        OptionGroup group = optionGroupRepository.findById(optionGroupId)
                .orElseThrow(() -> new IllegalArgumentException("옵션 그룹 없음"));

        menuOptionGroupRepository.save(new MenuOptionGroup(menu, group));
    }
    // 3. 옵션 그룹에 옵션 추가
    public MenuOption addOptionToGroup(Long optionGroupId, String optionName, int price) {
        OptionGroup group = optionGroupRepository.findById(optionGroupId)
                .orElseThrow(() -> new IllegalArgumentException("옵션 그룹 없음"));

        MenuOption option = new MenuOption();
        option.setName(optionName);
        option.setOptionPrice(price);
        option.setOptionGroup(group);

        return optionRepository.save(option);
    }


    @Transactional
    public MenuOptionResponseDto createMenuOption(Long menuId, Long optionGroupId, MenuOptionRequestDto dto) {
        // 1. 메뉴 조회
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 없습니다."));

        // 2. 옵션 그룹 조회
        OptionGroup optionGroup = optionGroupRepository.findById(optionGroupId)
                .orElseThrow(() -> new IllegalArgumentException("해당 옵션 그룹이 없습니다."));

        // 3. 메뉴-옵션그룹 연결 확인 (옵션 그룹이 이 메뉴에 연결되어 있는지)
        boolean connected = menuOptionGroupRepository.existsByMenuAndOptionGroup(menu, optionGroup);
        if (!connected) {
            throw new IllegalStateException("이 메뉴는 해당 옵션 그룹을 사용하지 않습니다.");
        }

        // 4. 옵션 생성 및 저장
        MenuOption option = new MenuOption();
        option.setName(dto.getName());
        option.setOptionPrice(dto.getOptionPrice());
        option.setOptionGroup(optionGroup);

        MenuOption savedOption = optionRepository.save(option);

        // 5. DTO로 변환 후 반환
        return new MenuOptionResponseDto(savedOption);
    }



}

//        public List<MenuOptionResponseDto> getMenuById(Long menuId) {
//        Menu menu = menuRepository.findById(menuId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 없습니다."));
//
//        return menu.getOptions().stream()
//                .map(MenuOptionResponseDto::new)
//                .collect(Collectors.toList());
//    }