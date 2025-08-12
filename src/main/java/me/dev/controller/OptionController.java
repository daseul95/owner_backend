package me.dev.controller;

import me.dev.dto.payload.request.CreateOptionDto;
import me.dev.entity.Option;
import me.dev.repository.OptionRepository;
import me.dev.repository.GroupRepository;
import me.dev.service.MenuGroupService;
import me.dev.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OptionController {


    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private MenuGroupService menuGroupService;

    @Autowired
    private OptionService optionService;

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
    public ResponseEntity<?> addOption(@RequestBody CreateOptionDto dto) {
        Option option= optionService.createOption(dto);
        return ResponseEntity.ok(option);
    }

}
