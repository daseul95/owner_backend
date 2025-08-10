package me.dev.controller;

import me.dev.dto.payload.request.CreateMenuOptionDto;
import me.dev.entity.MenuOption;
import me.dev.entity.OptionGroup;
import me.dev.repository.MenuOptionRepository;
import me.dev.repository.OptionGroupRepository;
import me.dev.service.MenuOptionGroupService;
import me.dev.service.MenuOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
public class MenOptionController {


    @Autowired
    private OptionGroupRepository optionGroupRepository;
    @Autowired
    private MenuOptionRepository menuOptionRepository;
    @Autowired
    private MenuOptionGroupService menuOptionGroupService;

    @Autowired
    private MenuOptionService menuOptionService;

    /*
    {"optionGroup" : 3,
    "name" : "토마토 빼기",
    "optionPrice" : 0
    }
     */
    // 옵션 그룹(토핑)에 옵션 추가 (POST)
    // /menuOption
    @PostMapping("/menuOption")
    @ResponseBody
    public ResponseEntity<?> addOption(@RequestBody CreateMenuOptionDto dto) {
        MenuOption option= menuOptionService.createMenuOption(dto);
        return ResponseEntity.ok(option);
    }

}
