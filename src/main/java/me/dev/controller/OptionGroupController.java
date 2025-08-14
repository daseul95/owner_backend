package me.dev.controller;

import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.DTO.MenuGroupDto;
import me.dev.dto.payload.DTO.OptionGroupDto;
import me.dev.service.MenuGroupService;
import me.dev.service.OptionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class OptionGroupController {
    @Autowired
    private OptionGroupService optionGroupService;

    //메뉴 옵션 그룹 생성
    /*
    {
      "menuId":1,
      "optionGroup": 1
     }
     */
    @PostMapping("/OptionGroup")
    @ResponseBody
    public ResponseEntity<OptionGroupDto> createOptionGroup(
            @RequestBody OptionGroupDto dto) {

        OptionGroupDto response = optionGroupService.createOptionCroup(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
