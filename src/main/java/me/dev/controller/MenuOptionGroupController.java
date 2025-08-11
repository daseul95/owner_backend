package me.dev.controller;

import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.DTO.MenuOptionGroupDto;
import me.dev.repository.OptionRepository;
import me.dev.repository.OptionGroupRepository;
import me.dev.service.MenuOptionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class MenuOptionGroupController {

    @Autowired
    private OptionGroupRepository optionGroupRepository;
    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private MenuOptionGroupService menuOptionGroupService;

    
    //메뉴 옵션 그룹 생성
    /*
    {
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
