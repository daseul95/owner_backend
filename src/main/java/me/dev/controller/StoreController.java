package me.dev.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.request.CreateStoreDto;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
          "businessNum": "070-1234-5678",
          "postNum": "06234",
          "description": "인터넷 카페입니다.",
          "phone": "02-1234-5678",
          "address": "서울특별시 동작구 어딘가",
          "lat": 43.535,
          "longti": 114.55,
          "image": "https://internet.com"     }
     */
    @PostMapping(value = "/store")
    @ResponseBody
    public ResponseEntity<?> createStore(@RequestBody CreateStoreDto dto,@AuthenticationPrincipal User userDetails) {
        User user = userDetails;
        storeService.createStore(dto,user);

        Map<String, Object> storeInfo = new HashMap<>();
        storeInfo.put("storeInfo", dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(storeInfo);
    }


    //가게 번호로 가게 하나 조회
    @GetMapping(value = "/store/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<StoreResponseDto> getStore(@PathVariable("id") Long id) {
        StoreResponseDto dto = storeService.getStoreById(id);

        return ResponseEntity.ok(dto);
    }


      //가게 모두 조회
    @GetMapping("/store")
    public ResponseEntity<?> getStores(@AuthenticationPrincipal User userDetails) {
        // 로그인한 유저 ID 가져오기
        Long userId = userDetails.getId();

        // 유저 ID로 스토어 조회
        List<Store> stores = storeService.getStoresByUserId(userId);

        List<StoreResponseDto> storeDto = stores.stream()
                .map( store -> new StoreResponseDto(store.getId(),store.getStoreName(),userId,store.getBusinessNum(),
                        store.getPostNum(),store.getDescription(),store.getPhone(),store.getAddress(),
                store.getLat(),store.getLongti(),store.getImage()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(storeDto);
        }

    //가게 정보 수정
    /*
    {
          "storeName": "헬로수정완",
          "businessNum": "070-1234-5678",
          "postNum": "06234",
          "description": "오프라인 카페입니다.",
          "phone": "02-1234-5678",
          "address": "서울특별시 동작구 어딘가",
          "lat": 43.535,
          "longti": 114.55,
          "image": "https://internet.com"     }
          중에서 골라서 수정가능
          수정안한거는 null 로 반환
     */
    @PutMapping("/store/{id}")
    public ResponseEntity<?> updateStore(@PathVariable("id") Long id, @RequestBody CreateStoreDto dto) {
        storeService.updateStore(id, dto);
        return ResponseEntity.ok().build();
    }

  //가게 정보 삭제
    @DeleteMapping("/store/{id}")
    public ResponseEntity<?> deleteStore(@PathVariable("id") Long id) {

        storeService.deleteStore(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}