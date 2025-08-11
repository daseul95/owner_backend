package me.dev.controller;

import me.dev.dto.payload.request.LoginRequest;
import me.dev.dto.payload.request.SignupRequest;
import me.dev.dto.payload.response.UserResponseDto;
import me.dev.entity.User;
import me.dev.repository.UserRepository;
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
    private UserService userService;
    @Autowired
    public BCryptPasswordEncoder passwordEncoder;


    // 유저 등록 (POST)

    /*
    {"userId":"osl123o","password":"12345678",
    "name":"me","email":"osl123o@naver.com",
    "nickname":"kaka"}
     */
    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<?> newUser(@RequestBody SignupRequest request) {

        User savedUser = userService.createUser(request,passwordEncoder);
        UserResponseDto userDto = new UserResponseDto();
        userDto.setUserId(savedUser.getUserId());
        userDto.setEmail(savedUser.getEmail());
        userDto.setName(savedUser.getName());
        userDto.setNickname(savedUser.getNickname());

        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }


    //유저 로그인 (POST)
    /*
      {"userId":"osl123o"
      ,"password":"12345678"}
     */
    @PostMapping(value="/signin")
    @ResponseBody
    public ResponseEntity<?> LoginUser(@RequestBody LoginRequest request) {
        Map<String, Object> user = userService.signin(request);
            return ResponseEntity.ok(user);

    }


}
