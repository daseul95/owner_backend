package me.dev.controller;

import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.DTO.MenuGroupDto;
import me.dev.repository.OptionRepository;
import me.dev.repository.GroupRepository;
import me.dev.service.MenuGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class MenuGroupController {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private MenuGroupService menuGroupService;

    
    //메뉴 옵션 그룹 생성
    /*
    {
      "menuId":1,
      "optionGroup": 1
     }
     */
    @PostMapping("/MenuGroup")
    @ResponseBody
    public ResponseEntity<MenuGroupDto> createMenuOptionGroup(
            @RequestBody MenuGroupDto dto) {

        MenuGroupDto response = menuGroupService.createMenuGroup(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
