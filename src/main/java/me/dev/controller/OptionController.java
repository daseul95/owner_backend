package me.dev.controller;

import me.dev.dto.payload.request.CreateOptionDto;
import me.dev.dto.payload.request.OptionRequestDto;
import me.dev.dto.payload.response.OptionResponseDto;
import me.dev.entity.Option;
import me.dev.repository.OptionRepository;
import me.dev.repository.GroupRepository;
import me.dev.service.MenuGroupService;
import me.dev.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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
     {
    "category" : "토핑",
    "name" : "토마토",
    "optionPrice" : 300,
    "des" : "상큼",
    "imgUrl" : "c://image/a"
    }
     */
    // 옵션 그룹(토핑)에 옵션 추가 (POST)
    // /option
    @PostMapping("/option")
    @ResponseBody
    public ResponseEntity<?> addOption(@RequestBody CreateOptionDto dto) {
        Option option= optionService.createOption(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(option);
    }

    // 옵션 모두 조회 (GET)
    @GetMapping("/option")
    public ResponseEntity<List<OptionResponseDto>> getOptions(){
        List<OptionResponseDto> options = optionService.getAllOptions();
        return ResponseEntity.ok(options);
    }

    // 옵션 하나 조회 (GET)
    // /option/{옵션번호}
    @GetMapping("/option/{id}")
    public ResponseEntity<OptionResponseDto> getOptionById(@PathVariable("id")Long id){
        OptionResponseDto option = optionService.getOptionById(id);
        return ResponseEntity.ok(option);
    }

    // 옵션 하나 수정 (PATCH)
    // /option/{옵션번호}
    @PatchMapping("/option/{id}")
    public ResponseEntity<?> updateOption(@PathVariable("id")Long id, @RequestBody OptionRequestDto dto) {
        optionService.updateOption(id, dto);
        return ResponseEntity.ok(dto);
    }

    //옵션 정보 삭제 (DELETE)
    @DeleteMapping("/option/{id}")
    public ResponseEntity<?> deleteOption(@PathVariable("id")Long id) {

        optionService.deleteOption(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

}
