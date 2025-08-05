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
    /*  {
          "storeName": "헬로헬로",
          "userId": 1,
          "businessNum": "070-1234-5678",
          "postNum": "06234",
          "description": "인터넷 카페입니다.",
          "phone": "02-1234-5678",
          "address": "서울특별시 동작구 어딘가",
          "lat": 43.535,
          "longti": 114.55,
          "image": "https://internet.com"     }
     */
    @PostMapping(value="/store/new")
    public ResponseEntity<?> createStore(@RequestBody StoreRequestDto dto) {
        storeService.createStore(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //가게 하나 조회 
    // url은 /store/스토어번호
    @GetMapping(value = "/store/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreResponseDto> getStore(@PathVariable("id") Long id) {
        StoreResponseDto dto = storeService.getStoreById(id);
        return ResponseEntity.ok(dto);
    }

    //가게 정보 수정
    // url은 /store/스토어번호
    /*
    {
          "storeName": "헬로헬로",
          "userId": 1,
          "businessNum": "070-1234-5678",
          "postNum": "06234",
          "description": "인터넷 카페입니다.",
          "phone": "02-1234-5678",
          "address": "서울특별시 동작구 어딘가",
          "lat": 43.535,
          "longti": 114.55,
          "image": "https://internet.com"     }
          중에서 골라서 수정가능
          수정안한거는 null 로 반환
     */
    @PutMapping("/store/{id}")
    public ResponseEntity<?> updateStore(@PathVariable("id") Long id, @RequestBody StoreRequestDto dto){
        storeService.updateStore(id,dto);
        return ResponseEntity.ok().build();
    }
}