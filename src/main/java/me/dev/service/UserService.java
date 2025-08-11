package me.dev.service;

import me.dev.dto.payload.request.LoginRequest;
import me.dev.dto.payload.request.SignupRequest;
import me.dev.dto.payload.response.UserResponseDto;
import me.dev.entity.User;
import me.dev.repository.UserRepository;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.context.annotation.Lazy;



@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Lazy
    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Override
    public User loadUserByUsername(String UserId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(UserId);

        if (user == null) {
            throw new UsernameNotFoundException("해당 아이디의 이용자를 찾을 수 없습니다. ");
        }
        System.out.println("loadUserByUsername ✅ 아이디 있음: " + user.getUserId());
        System.out.println("✅ 비밀번호 해시: " + user.getPassword());

        return user;
    }

    public User createUser(SignupRequest request, PasswordEncoder passwordEncoder) {
        User user = new User();

        String password = passwordEncoder.encode(request.getPassword());
        user.setUserId(request.getUserId());
        user.setPassword(password);
        user.setUsername(request.getName());
        user.setEmail(request.getEmail());
        user.setNickname(request.getNickname());

        // DB에 저장
        return userRepository.save(user);
    }

    public Map<String, Object> signin(LoginRequest request) {
        System.out.println(request.getUserId());
        System.out.println(request.getPassword());

        UserResponseDto userDto = new UserResponseDto();

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
            resaveUser(user);
            User userInfo = findByUserId(request.getUserId());

            userDto.setUserId(userInfo.getUserId());
            userDto.setEmail(userInfo.getEmail());
            userDto.setName(userInfo.getUsername());
            userDto.setNickname(userInfo.getNickname());


            Map<String, Object> tokens = new HashMap<>();
            tokens.put("accessToken", accessToken);
            tokens.put("refreshToken", refreshToken);
            tokens.put("userInfo", userDto);

            return tokens;


        } catch (BadCredentialsException ex) {
            Map<String, Object> error = new HashMap<>();
            String word = "로그인 실패: 비밀번호 또는 이메일이 일치하지 않음";
            error.put("error",word);
            return error;

        }
    }



    public User resaveUser(User member) {
        return userRepository.save(member);
    }

    private void validateDuplicateMember(User user) {
        User findUser = userRepository.findByUserId(user.getUserId());
        if (findUser != null) {
            throw new IllegalStateException("이미 가입된 회원입니다");
        }
    }

    public User findById(Long Id){
        Optional<User> optionalUser = userRepository.findById(Id);

        User user = optionalUser.orElseThrow(() -> new RuntimeException("유저가 없습니다"));
        return user;
    }
    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    public User findByName(String name) {
        return userRepository.findByUsername(name);
    }

    public User save(SignupRequest userDto) {
        // DTO -> Entity 변환
        User user = new User();

        user.setPassword(userDto.getPassword()); // 암호화 필요!
        user.setUsername(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setNickname(userDto.getNickname());

        // DB에 저장
        return userRepository.save(user);
    }

    public String findPasswordById(Long id){
        return userRepository.findPasswordById(id);
    }
}
