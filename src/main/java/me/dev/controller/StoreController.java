package me.dev.controller;

import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.request.StoreRequestDto;
import me.dev.dto.payload.response.StoreResponseDto;
import me.dev.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    // 가게 등록
    @PostMapping(value="/store/new")
    public ResponseEntity<?> createStore(@RequestBody StoreRequestDto dto) {
        storeService.createStore(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //가게 하나 조회
    @GetMapping(value = "/store/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreResponseDto> getStore(@PathVariable("id") Long id) {
        StoreResponseDto dto = storeService.getStoreById(id);
        return ResponseEntity.ok(dto);
    }

    //가게 정보 수정
    @PutMapping("/store/{id}")
    public ResponseEntity<?> updateStore(@PathVariable("id") Long id, @RequestBody StoreRequestDto dto){
        storeService.updateStore(id,dto);
        return ResponseEntity.ok().build();
    }
}