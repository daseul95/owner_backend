package me.dev.controller;

import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.DTO.GroupDto;
import me.dev.dto.payload.DTO.MenuDto;
import me.dev.dto.payload.DTO.MenuGroupDto;
import me.dev.dto.payload.request.CreateMenuDTO;
import me.dev.dto.payload.request.MenuRequestDto;
import me.dev.dto.payload.request.StoreRequestDto;
import me.dev.dto.payload.response.MenuResponseDto;
import me.dev.dto.payload.response.StoreResponseDto;
import me.dev.entity.Menu;
import me.dev.entity.MenuGroup;
import me.dev.entity.Store;
import me.dev.entity.User;
import me.dev.service.MenuGroupService;
import me.dev.service.MenuService;
import me.dev.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private MenuGroupService menuGroupService;


    // 메뉴 하나 등록 (POST)
    // /menu
    /*
    {

          "name":"햄토스트",
          "des":"햄이 들어가있어요",
          "imgUrl":"C://example.toast/ham",
          "price":5000
        }

     */
    @PostMapping(value = "/menu", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createMenu(
            @AuthenticationPrincipal User userDetails,
            @RequestBody CreateMenuDTO dto
    ) {
        Long userId = userDetails.getId();
        Long storeId = storeService.getStoreIdByUserId(userId);
        Menu menu = menuService.createMenu(storeId,userId,dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(menu);
    }



    // 전체 메뉴 이름 조회 (GET)
    @GetMapping("/menu")
    public ResponseEntity<List<MenuResponseDto>> getMenus(@AuthenticationPrincipal User userDetails) {
        Long userId = userDetails.getId();
        List<MenuResponseDto> menus = menuService.getMenusByUserId(userId);
        return ResponseEntity.ok(menus);
    }

    // 메뉴 하나 조회 (GET)
    // /menu/{메뉴번호}
    @GetMapping("/menu/{id}")
    public ResponseEntity<MenuResponseDto> getMenuNameById(@PathVariable("id") Long id) {
        MenuResponseDto name = menuService.getMenuById(id);
        return ResponseEntity.ok(name);
    }

    /*
        {
          "category" : "토스트",
          "name":"치즈 토스트",
          "des":"치즈가 들어가있어요",
          "imgUrl":"C://example.toast/ham",
          "price":6000
        }
     */
    // 메뉴 하나 수정 (PATCH)
    // /menu/{메뉴번호}
    @PatchMapping("/menu/{id}")
    public ResponseEntity<?> updateMenu(@PathVariable("id") Long id, @RequestBody MenuDto dto) {
        Menu updateMenu = menuService.updateMenu(id, dto);
        return ResponseEntity.ok(updateMenu);
    }


    //메뉴 하나 삭제 (DELETE)
    // /menu/{메뉴번호}
    @DeleteMapping("/menu/{id}")
    public ResponseEntity<?> deleteToast(@PathVariable("id") Long id) {

        menuService.deleteMenu(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }


    @PostMapping("/menu/group")
    public ResponseEntity<?> createMenuGroup(@RequestBody MenuGroupDto mgDto){
        MenuGroupDto menuGroupDTO = menuGroupService.createMenuGroup(mgDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(menuGroupDTO);
    }



}