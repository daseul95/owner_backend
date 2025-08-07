package me.dev.controller;

import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.request.StoreRequestDto;
import me.dev.dto.payload.response.StoreResponseDto;
import me.dev.entity.Store;
import me.dev.entity.User;
import me.dev.service.StoreService;
import me.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class StoreController {


    @Autowired
    private StoreService storeService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;


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
    @PostMapping(value = "/store/new")
    @ResponseBody
    public ResponseEntity<?> createStore(@RequestBody StoreRequestDto dto) {

        storeService.createStore(dto);

        Map<String, Object> storeInfo = new HashMap<>();
        storeInfo.put("storeInfo", dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(storeInfo);
    }

    //가게 번호로 가게 하나 조회
    //       /store/스토어번호
    @GetMapping(value = "/store/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<StoreResponseDto> getStore(@PathVariable("id") Long id) {
        StoreResponseDto dto = storeService.getStoreById(id);

        return ResponseEntity.ok(dto);
    }


    //유저 번호로 가게 하나 조회
    //      /{유저번호}/store
    @GetMapping("/{id}/store")
    public ResponseEntity<?> getStoreByUserId(@PathVariable("id") Long id) {
        try {
            Store store = storeService.getStoreByUserId(id);
            return ResponseEntity.ok(store);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //유저 번호로 가게 모두 조회
    //      /{유저번호}/stores
    @GetMapping("/{id}/stores")
    public ResponseEntity<?> getStoresByUserId(@PathVariable("id") Long id) {
        try {
            List<Store> stores = storeService.getStoresByUserId(id); // ← 복수형 메소드명도 권장!
            return ResponseEntity.ok(stores);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    //가게 정보 수정
    //      /store/스토어번호
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
    public ResponseEntity<?> updateStore(@PathVariable("id") Long id, @RequestBody StoreRequestDto dto) {
        storeService.updateStore(id, dto);
        return ResponseEntity.ok().build();
    }

        // "/store/1/1/delete"
    @DeleteMapping("/store/{user_id}/{store_id}/delete")
    public ResponseEntity<?> deleteStore(@PathVariable("user_id") Long userId,
                                         @PathVariable("store_id") Long storeId) {

        storeService.deleteStore(storeId);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}