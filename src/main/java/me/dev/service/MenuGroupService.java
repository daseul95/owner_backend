package me.dev.service;

import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.DTO.MenuGroupDto;
import me.dev.entity.Group;
import me.dev.entity.Menu;
import me.dev.entity.MenuGroup;
import me.dev.repository.MenuGroupRepository;
import me.dev.repository.OptionRepository;
import me.dev.repository.MenuRepository;
import me.dev.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuGroupService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private MenuGroupRepository menuGroupRepository;
    @Autowired
    private OptionRepository optionRepository;

    public MenuGroupDto createMenuGroup(MenuGroupDto dto) {
        Menu menu = menuRepository.findById(dto.getMenuId())
                .orElseThrow(() -> new IllegalArgumentException("메뉴가 없습니다."));
        Group group = groupRepository.findById(dto.getGroup())
                .orElseThrow(() -> new IllegalArgumentException("옵션 그룹이 없습니다."));

        MenuGroup menuGroup = new MenuGroup();
        menuGroup.setMenu(menu);
        menuGroup.setGroup(group);

        MenuGroup saved = menuGroupRepository.save(menuGroup);
        return new MenuGroupDto(saved);
    }

}
