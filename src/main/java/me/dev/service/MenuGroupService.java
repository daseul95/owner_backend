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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuGroupService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private MenuGroupRepository mgRepository;
    @Autowired
    private OptionRepository optionRepository;

    public MenuGroupDto createMenuGroup(MenuGroupDto dto) {
        Menu menu = menuRepository.findById(dto.getMenuId())
                .orElseThrow(() -> new IllegalArgumentException("메뉴가 없습니다."));

        MenuGroup menuGroup = new MenuGroup();
        menuGroup.setMenu(menu);

        List<Group> groups = groupRepository.findAllByIdIn(dto.getGroupId());
        List<MenuGroup> mg = groups.stream()
                .map(group -> {
                    MenuGroup menuG = new MenuGroup();
                    menuG.setMenu(menu);
                    menuG.setGroup(group);
                    return menuG;
                })
                .toList();

        mgRepository.saveAll(mg);
        return dto;
    }

}
