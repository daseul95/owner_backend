package me.dev.controller;

import me.dev.dto.payload.DTO.GroupDto;
import me.dev.entity.Group;
import me.dev.repository.OptionRepository;
import me.dev.repository.GroupRepository;
import me.dev.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private GroupService groupService;

    // 옵션 그룹 추가
  /*
  {
        "name":"토핑",
         "description":"맛을 더해줍니다"    }
  */
    @PostMapping("/group")
    public Group createGroup(@RequestBody GroupDto dto) {

        Group group = groupService.createGroup(dto);

        return groupRepository.save(group);
    }



    // 그룹 전체 조회

}
