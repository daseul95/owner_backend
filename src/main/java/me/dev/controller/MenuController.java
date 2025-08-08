package me.dev.controller;

import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.request.MenuRequestDto;
import me.dev.dto.payload.request.StoreRequestDto;
import me.dev.dto.payload.response.MenuResponseDto;
import me.dev.dto.payload.response.StoreResponseDto;
import me.dev.entity.Menu;
import me.dev.entity.Store;
import me.dev.entity.User;
import me.dev.service.MenuService;
import me.dev.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private StoreService storeService;



    // 메뉴 하나 등록
    // /{가게 번호}/menu
    /*
    {
          "category" : "토스트",
          "name":"햄토스트",
          "des":"햄이 들어가있어요",
          "imgUrl":"C://example.toast/ham",
          "price":5000
        }

     */
    @PostMapping(value = "/menu", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createMenu(
            @AuthenticationPrincipal User userDetails,
            @RequestBody MenuRequestDto dto
    ) {
        Long userId = userDetails.getId();
        System.out.println("Menu 생성할 때 들어온 userId : " + userId);
        Long storeId = storeService.getStoreIdByUserId(userId);
        System.out.println("Menu 생성할 때 찾아낸 storeId : " + storeId);
        Menu menu = menuService.createMenu(storeId,dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(menu);
    }

    // 전체 메뉴 이름 조회
    @GetMapping("/menu")
    public ResponseEntity<List<MenuResponseDto>> getMenus(@AuthenticationPrincipal User userDetails) {
        Long userId = userDetails.getId();
        List<MenuResponseDto> menus = menuService.getMenusByUserId(userId);
        return ResponseEntity.ok(menus);
    }

    // 메뉴 하나 조회
    // /menu/{메뉴번호}
    @GetMapping("/menu/{id}")
    public ResponseEntity<MenuResponseDto> getMenuNameById(@PathVariable("id") Long id) {
        MenuResponseDto name = menuService.getMenuById(id);
        return ResponseEntity.ok(name);
    }

    // 메뉴 하나 수정
    @PutMapping("/menu/{id}")
    public ResponseEntity<?> updateMenu(@PathVariable("id") Long id, @RequestBody MenuRequestDto dto) {
        menuService.updateMenu(id, dto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/toast/{user_id}/{toast_id}/delete")
    public ResponseEntity<?> deleteToast(@PathVariable("user_id") Long userId,
                                         @PathVariable("toast_id") Long toastId) {

        menuService.deleteMenu(toastId);
        return ResponseEntity.noContent().build(); // 204 No Content
    }



}