package me.dev.controller;

import jakarta.transaction.Transactional;
import me.dev.dto.payload.DTO.GroupDto;
import me.dev.dto.payload.DTO.OptionByGroupDto;
import me.dev.entity.Group;
import me.dev.entity.Option;
import me.dev.entity.OptionByGroup;
import me.dev.repository.OptionByGroupRepository;
import me.dev.repository.OptionRepository;
import me.dev.repository.GroupRepository;
import me.dev.service.GroupService;
import me.dev.service.OptionByGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private OptionByGroupRepository ogRepository;

    @Autowired
    private OptionByGroupService optionByGroupService;


    // 그룹 추가 (POST)
  /*
  {
        "name":"토핑",
         "des":"맛을 더해줍니다"    }
  */
    @PostMapping("/group")
    public GroupDto createGroup(@RequestBody GroupDto dto) {

        Group group = groupService.createGroup(dto);

        return dto;
    }


    // 그룹 모두 조회 (GET)
    @GetMapping("/group")
    public ResponseEntity<List<GroupDto>> getGroups() {
        List<GroupDto> dto = groupService.getAllGroups();
        return ResponseEntity.ok(dto);
    }

    // 그룹 하나 조회 (GET)
    @GetMapping("/group/{id}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable("id") Long id) {
        GroupDto dto = groupService.getGroupById(id);
        return ResponseEntity.ok(dto);
    }


    // PATCH: 특정 필드만 수정
    @PatchMapping("/group/{id}")
    public ResponseEntity<?> updateGroup(@PathVariable("id") Long id,
                                         @RequestBody GroupDto dto) {
        Group updatedGroup =groupService.updateGroup(id, dto);
        return ResponseEntity.ok(updatedGroup);
    }

    //그룹 하나 삭제 (DELETE)
    @DeleteMapping("/group/{id}")
    public void deleteGroup(@PathVariable("id") Long id){
        groupService.deleteGroup(id);
    }


    //옵션과 함께 하나의 그룹 생성 (POST)
    /*
  {
     {"groupId": 4,
  "optionId": [2, 3, 4]
  }
     */
    @PostMapping("/group/option")
    public ResponseEntity<?> createOptionByGroup(@RequestBody OptionByGroupDto ogDto) {

        return optionByGroupService.createOptionByGroup(ogDto);
    }

    // 그룹에 등록된 옵션찾기 (GET)
    @GetMapping("/group/{id}/option")
    public ResponseEntity<?> getOptionByGroup(@PathVariable("id") Long id){
        OptionByGroupDto resultOgDto = optionByGroupService.getOptionByGroup(id);
        return ResponseEntity.ok(resultOgDto);
    }



    // 그룹 바꾸기 (PATCH)
    /*
     {"groupId": 4}
     */
    @PatchMapping("/group/{id}/option")
    public ResponseEntity<?> patchOptionByGroup(@PathVariable("id") Long id,@RequestBody OptionByGroupDto dto){
        String result = optionByGroupService.patchOptionByGroup(id,dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/group/{id}/option")
    public ResponseEntity<?> deleteGroupOptions(@PathVariable("id") Long id) {
        String result = optionByGroupService.deleteOptionByGroup(id);
        return ResponseEntity.ok(result);
    }

}



