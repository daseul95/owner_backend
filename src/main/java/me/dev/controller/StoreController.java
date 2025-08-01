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
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    public ResponseEntity<?> createStore(@RequestBody StoreRequestDto dto) {
        storeService.createStore(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreResponseDto> getStore(@PathVariable("id") Long id) {
        StoreResponseDto dto = storeService.getStoreById(id);
        return ResponseEntity.ok(dto);
    }
}