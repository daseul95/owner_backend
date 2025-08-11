package me.dev.controller;

import me.dev.dto.payload.request.LoginRequest;
import me.dev.dto.payload.request.SignupRequest;
import me.dev.dto.payload.response.UserResponseDto;
import me.dev.entity.User;
import me.dev.service.JwtTokenProvider;
import me.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {


    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsService userDetailsService;

    // 유저 등록 (POST)
    // {"userId":"osl123o","password":"12345678"}
    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<?> newUser(@RequestBody SignupRequest request) {

        User savedUser = userService.createUser(request,passwordEncoder);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }


    //유저 로그인 (POST)
    /*
      {"userId":"osl123o"
      ,"password":"12345678"}
     */
    @PostMapping(value="/signin")
    @ResponseBody
    public ResponseEntity<?> LoginUser(@RequestBody LoginRequest request) {

        System.out.println(request.getUserId());
        System.out.println(request.getPassword());

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserId(), request.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = (User) authentication.getPrincipal();
            String accessToken = jwtTokenProvider.generateJwtToken(authentication);
            String refreshToken = jwtTokenProvider.generateJwtToken(authentication);

            // 2. 리프레시 토큰 저장
            user.setRefreshToken(refreshToken);
            userService.resaveUser(user);
            User userInfo = userService.findByUserId(request.getUserId());

            UserResponseDto userDto = new UserResponseDto();
            userDto.setUserId(userInfo.getUserId());
            userDto.setName(userInfo.getCeoName());
            userDto.setNickname(userInfo.getNickname());

            Map<String, Object> tokens = new HashMap<>();
            tokens.put("accessToken", accessToken);
            tokens.put("refreshToken", refreshToken);
            tokens.put("userInfo", userDto);

            return ResponseEntity.ok(tokens);

        } catch (BadCredentialsException ex) {
            System.err.println("로그인 실패: 비밀번호 또는 이메일이 일치하지 않음");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패: 아이디 또는 비밀번호가 잘못되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 에러");
        }
    }


}
