package me.dev.controller;

import me.dev.dto.payload.DTO.OptionGroupDto;
import me.dev.entity.OptionGroup;
import me.dev.repository.OptionRepository;
import me.dev.repository.OptionGroupRepository;
import me.dev.service.OptionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class OptionGroupController {

    @Autowired
    private OptionGroupRepository optionGroupRepository;
    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private OptionGroupService optionGroupService;

    // 옵션 그룹 추가
  /*
  {
        "name":"토핑",
         "description":"맛을 더해줍니다"    }
  */
    @PostMapping("/optionGroup")
    public OptionGroup createGroup(@RequestBody OptionGroupDto dto) {

        OptionGroup group = optionGroupService.createOptionGroup(dto);

        return optionGroupRepository.save(group);
    }



    // 그룹 전체 조회

}
