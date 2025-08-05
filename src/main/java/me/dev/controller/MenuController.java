package me.dev.controller;

import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.request.MenuRequestDto;
import me.dev.dto.payload.response.MenuOptionResponseDto;
import me.dev.dto.payload.response.MenuResponseDto;
import me.dev.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;


    // 메뉴 하나 등록
    // /{가게 번호}/menu
    /*
    {
          "category" : "토스트",
          "name":"햄토스트",
          "des":"햄이 들어가있어요",
          "imgUrl":"C://example.toast/ham",
          "price":"5000"
        }

     */
    @PostMapping(value = "/{id}/menu", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuResponseDto> createMenu(
            @PathVariable("id") Long storeId,
            @RequestBody MenuRequestDto dto
    ) {
        MenuResponseDto response = menuService.createMenu(storeId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 전체 메뉴 이름 조회
    @GetMapping("/menu/names") 
    public ResponseEntity<List<String>> getMenuNames() {
        List<String> names = menuService.getMenuNames();
        return ResponseEntity.ok(names);
    }

    // 메뉴 하나 조회
    // /menu/{메뉴번호}
    @GetMapping("/menu/{id}")
    public ResponseEntity<String> getMenuNameById(@PathVariable("id") Long id) {
        String name = menuService.getMenuNameById(id);
        return ResponseEntity.ok(name);
    }



}