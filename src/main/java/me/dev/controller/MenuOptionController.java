package me.dev.controller;

import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.request.MenuOptionRequestDto;
import me.dev.dto.payload.response.MenuOptionResponseDto;
import me.dev.service.MenuOptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuOptionController {

    private final MenuOptionService menuOptionService;


    // 메뉴 옵션 전체 조회
    // /{메뉴 번호(ex)토스트번호(1))}/options
    @GetMapping("/{id}/options")
    public ResponseEntity<List<MenuOptionResponseDto>> getOptions(
            @PathVariable("id") Long menuId) {

        List<MenuOptionResponseDto> options = menuOptionService.getMenuById(menuId);
        return ResponseEntity.ok(options);
    }
    // 메뉴 옵션 하나 등록
    // /{메뉴 번호(ex)토스트번호(1))}/option
    // 토스트의 옵션 등록
    /*
    {"name":"햄추가","price":"1000"}
     */
    @PostMapping("/{id}/option")
    public ResponseEntity<MenuOptionResponseDto> createMenuOption(
            @PathVariable("id") Long menuId,
            @RequestBody MenuOptionRequestDto dto
    ) {
        MenuOptionResponseDto response = menuOptionService.createMenuOption(menuId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
