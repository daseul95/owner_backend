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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/user")
    @ResponseBody
    public ResponseEntity<?> newUser(@RequestBody SignupRequest request) {

        User savedUser = userService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping(value="/login")
    @ResponseBody
    public ResponseEntity<?> LoginUser(@RequestBody LoginRequest request) {

        System.out.println(request.getEmail());
        System.out.println(request.getPassword());

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            System.out.println(authentication);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = (User) authentication.getPrincipal();
            String accessToken = jwtTokenProvider.generateJwtToken(authentication);
            String refreshToken = jwtTokenProvider.generateJwtToken(authentication);

            // 2. 리프레시 토큰 저장
            user.setRefreshToken(refreshToken);
            userService.resaveMember(user);

            String token = jwtTokenProvider.generateJwtToken(authentication);
            Long id = userService.findByEmail(request.getEmail()).getId();
            String userName = userService
                    .findByEmail(request.getEmail()).getName();

            return ResponseEntity.ok(new JwtResponse(accessToken, id, request.getEmail(), userName)); // ✅ JSON 형태로 JWT 보내야 함
        } catch (BadCredentialsException ex) {
            System.out.println("비밀번호 또는 아이디 불일치");
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    @GetMapping(value="/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg","아이디 또는 비밀번호를 확인해주세요");
        return "/member/memberLoginForm";
    }

}
