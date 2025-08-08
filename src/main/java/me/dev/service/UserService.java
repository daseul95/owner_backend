package me.dev.service;

import me.dev.dto.payload.request.SignupRequest;
import me.dev.entity.User;
import me.dev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("해당 이메일의 사용자를 찾을 수 없습니다 ");
        }
        System.out.println("loadUserByUsername ✅ 이메일 있음: " + user.getEmail());
        System.out.println("✅ 비밀번호 해시: " + user.getPassword());

        return user;
    }

    public User createUser(SignupRequest request, PasswordEncoder passwordEncoder){
        User user = new User();

        String password= passwordEncoder.encode(request.getPassword());
        user.setLoginId(request.getUserId());
        user.setPassword(password);
        user.setCeoName(request.getName());
        user.setEmail(request.getEmail());
        user.setNickname(request.getNickname());

        // DB에 저장
        return userRepository.save(user);
    }

    public User resaveUser(User member) {
        return userRepository.save(member);
    }

    private void validateDuplicateMember(User user) {
        User findUser = userRepository.findByEmail(user.getEmail());
        if (findUser != null) {
            throw new IllegalStateException("이미 가입된 회원입니다");
        }
    }

    public User findById(Long userId){
        Optional<User> optionalUser = userRepository.findById(userId);

        User user = optionalUser.orElseThrow(() -> new RuntimeException("유저가 없습니다"));
        return user;
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByName(String name) {
        return userRepository.findByCeoName(name);
    }

    public User save(SignupRequest userDto) {
        // DTO -> Entity 변환
        User user = new User();

        user.setPassword(userDto.getPassword()); // 암호화 필요!
        user.setCeoName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setNickname(userDto.getNickname());

        // DB에 저장
        return userRepository.save(user);
    }

    public String findPasswordById(Long id){
        return userRepository.findPasswordById(id);
    }
}
