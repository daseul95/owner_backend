package me.dev.controller;

import me.dev.dto.payload.request.LoginRequest;
import me.dev.dto.payload.request.SignupRequest;
import me.dev.dto.payload.response.JwtResponse;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserContoller {


    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    UserDetailsService userDetailsService;

    // 유저 등록
    @PostMapping("/user/new")
    @ResponseBody
    public ResponseEntity<?> newUser(@RequestBody SignupRequest request) {

        User savedUser = userService.createUser(request,passwordEncoder);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    //유저 로그인
    /*
      {"email":"osl123o@naver.com"
      ,"password":"12345678"}
     */
    @PostMapping(value="/user/login")
    @ResponseBody
    public ResponseEntity<?> LoginUser(@RequestBody LoginRequest request) {

        System.out.println(request.getEmail());
        System.out.println(request.getPassword());

        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

            boolean matches = passwordEncoder.matches(request.getPassword(), userDetails.getPassword());
            System.out.println("Password match result: " + matches);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            System.out.println("[login] email: " + request.getEmail());
            System.out.println("[login] password: " + request.getPassword());

            User user = (User) authentication.getPrincipal();
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String accessToken = jwtTokenProvider.generateJwtToken(authentication);
            String refreshToken = jwtTokenProvider.generateJwtToken(authentication);

            // 2. 리프레시 토큰 저장
            user.setRefreshToken(refreshToken);
            userService.resaveUser(user);
            User userInfo = userService.findByEmail(request.getEmail());

            Map<String, Object> tokens = new HashMap<>();
            tokens.put("accessToken", accessToken);
            tokens.put("refreshToken", refreshToken);
            tokens.put("userInfo", userInfo);

            return ResponseEntity.status(HttpStatus.CREATED).body(tokens);

        } catch (Exception e) {
            e.printStackTrace(); // 여기서 정확한 예외 확인 가능
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패: " + e.getMessage());
        }

    }


    @GetMapping(value="/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg","아이디 또는 비밀번호를 확인해주세요");
        return "/member/memberLoginForm";
    }

}
