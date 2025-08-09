package me.dev.controller;

import me.dev.entity.MenuOption;
import me.dev.entity.OptionGroup;
import me.dev.repository.MenuOptionRepository;
import me.dev.repository.OptionGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping
public class OptionGroupController {

    @Autowired
    private OptionGroupRepository optionGroupRepository;
    @Autowired
    private MenuOptionRepository menuOptionRepository;

    // 옵션 그룹 추가
  /*
  {
        "name":"토핑",
         "description":"맛을 더해줍니다"    }
  */
    @PostMapping("/optionGroup")
    public OptionGroup createGroup(@RequestBody OptionGroup group) {
        return optionGroupRepository.save(group);
    }


    // 옵션 그룹(토핑)에 옵션 추가
    @PostMapping("/{groupId}/{menuOptionId}")
    public MenuOption addOption(@PathVariable("groupId") Long groupId, @PathVariable("menuOptionId") Long menuOptionId) {
        OptionGroup group = optionGroupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("옵션 그룹 없음"));
        MenuOption option = menuOptionRepository. findById(menuOptionId)
                .orElseThrow(()->new NoSuchElementException("MenuOption with id " + menuOptionId + " not found"));
        option.setOptionGroup(group);
        return menuOptionRepository.save(option);
    }

    // 그룹 전체 조회
//    @GetMapping
//    public List<OptionGroup> getGroups() {
//        return optionGroupRepository.findAll();
//    }



//
//    @GetMapping
//    public List<OptionGroup> getAllOptionGroups() {
//        return optionGroupRepository.findAll();
//    }


}
