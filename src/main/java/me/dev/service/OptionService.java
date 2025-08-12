package me.dev.service;

import jakarta.transaction.Transactional;
import me.dev.dto.payload.request.CreateOptionDto;
import me.dev.entity.Option;
import me.dev.repository.MenuGroupRepository;
import me.dev.repository.OptionRepository;
import me.dev.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OptionService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private MenuGroupRepository menuGroupRepository;
    @Autowired
    private OptionRepository optionRepository;



    // 옵션 추가
    @Transactional
    public Option createOption(CreateOptionDto dto) {

        Option option = new Option();
        option.setName(dto.getName());
        option.setOptionPrice(dto.getOptionPrice());
        option.setImgUrl(dto.getImgUrl());
        Option savedOption = optionRepository.save(option);

        return savedOption;
    }

    // 2. 메뉴에 옵션 그룹 연결
//    public void connectMenuAndOptionGroup(Long menuId, Long optionGroupId) {
//        Menu menu = menuRepository.findById(menuId)
//                .orElseThrow(() -> new IllegalArgumentException("메뉴 없음"));
//        OptionGroup group = optionGroupRepository.findById(optionGroupId)
//                .orElseThrow(() -> new IllegalArgumentException("옵션 그룹 없음"));
//
//        menuOptionGroupRepository.save(new MenuOptionGroup(menu, group));
//    }
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

