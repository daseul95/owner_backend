package me.dev.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.request.CreateMenuOptionDto;
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
    private MenuRepository menuRepository;
    @Autowired
    private OptionGroupRepository optionGroupRepository;
    @Autowired
    private MenuOptionGroupRepository menuOptionGroupRepository;
    @Autowired
    private MenuOptionRepository menuOptionRepository;



    // 옵션 그룹(토핑)에 옵션 추가
    @Transactional
    public MenuOption createMenuOption(CreateMenuOptionDto dto) {

        OptionGroup group = optionGroupRepository.findById(dto.getOptionGroup())
                .orElseThrow(() -> new IllegalArgumentException("옵션 그룹 없음"));
        String optionGroupName = group.getName();
        MenuOption option = new MenuOption();
        option.setName(dto.getName());
        option.setOptionPrice(dto.getOptionPrice());
        option.setOptionGroup(group);
        option.setCategory(optionGroupName);
        MenuOption savedOption = menuOptionRepository.save(option);

        return savedOption;
    }

    // 2. 메뉴에 옵션 그룹 연결
    public void connectMenuAndOptionGroup(Long menuId, Long optionGroupId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("메뉴 없음"));
        OptionGroup group = optionGroupRepository.findById(optionGroupId)
                .orElseThrow(() -> new IllegalArgumentException("옵션 그룹 없음"));

        menuOptionGroupRepository.save(new MenuOptionGroup(menu, group));
    }
}   //     3. 옵션 그룹에 옵션 추가
//    public MenuOption addOptionToGroup(Long optionGroupId, String optionName, int price) {
//    OptionGroup group = optionGroupRepository.findById(optionGroupId)
//            .orElseThrow(() -> new IllegalArgumentException("옵션 그룹 없음"));
//
//    MenuOption option = new MenuOption();
//    option.setName(optionName);
//    option.setOptionPrice(price);
//    option.setOptionGroup(group);
//
//    return optionRepository.save(option);

