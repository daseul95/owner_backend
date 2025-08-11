package me.dev.service;

import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.DTO.MenuOptionGroupDto;
import me.dev.entity.Menu;
import me.dev.entity.MenuOptionGroup;
import me.dev.entity.OptionGroup;
import me.dev.repository.MenuOptionGroupRepository;
import me.dev.repository.OptionRepository;
import me.dev.repository.MenuRepository;
import me.dev.repository.OptionGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuOptionGroupService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private OptionGroupRepository optionGroupRepository;
    @Autowired
    private MenuOptionGroupRepository menuoptionGroupRepository;
    @Autowired
    private OptionRepository optionRepository;

    public MenuOptionGroupDto createMenuOptionGroup(MenuOptionGroupDto dto) {
        Menu menu = menuRepository.findById(dto.getMenuId())
                .orElseThrow(() -> new IllegalArgumentException("메뉴가 없습니다."));
        OptionGroup optionGroup = optionGroupRepository.findById(dto.getOptionGroup())
                .orElseThrow(() -> new IllegalArgumentException("옵션 그룹이 없습니다."));

        MenuOptionGroup menuGroup = new MenuOptionGroup();
        menuGroup.setMenu(menu);
        menuGroup.setOptionGroup(optionGroup);

        MenuOptionGroup saved = menuoptionGroupRepository.save(menuGroup);
        return new MenuOptionGroupDto(saved);
    }

}
