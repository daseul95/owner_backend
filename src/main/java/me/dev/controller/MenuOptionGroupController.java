package me.dev.controller;

import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.DTO.MenuOptionGroupDto;
import me.dev.dto.payload.request.MenuOptionRequestDto;
import me.dev.dto.payload.response.MenuOptionResponseDto;
import me.dev.entity.MenuOption;
import me.dev.entity.OptionGroup;
import me.dev.repository.MenuOptionRepository;
import me.dev.repository.OptionGroupRepository;
import me.dev.service.MenuOptionGroupService;
import me.dev.service.MenuOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class MenuOptionGroupController {

    @Autowired
    private OptionGroupRepository optionGroupRepository;
    @Autowired
    private MenuOptionRepository menuOptionRepository;
    @Autowired
    private MenuOptionGroupService menuOptionGroupService;

    
    //메뉴 옵션 그룹 생성
    /*
    {
    "id":1,
      "menuId":1,
      "optionGroup": 1
     }
     */
    @PostMapping("/MenuOptionGroup")
    @ResponseBody
    public ResponseEntity<MenuOptionGroupDto> createMenuOptionGroup(
            @RequestBody MenuOptionGroupDto dto) {

        MenuOptionGroupDto response = menuOptionGroupService.createMenuOptionGroup(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
