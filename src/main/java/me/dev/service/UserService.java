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
    public User loadUserByUsername(String UserId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(UserId);

        if (user == null) {
            throw new UsernameNotFoundException("해당 아이디의 이용자를 찾을 수 없습니다. ");
        }
        System.out.println("loadUserByUsername ✅ 아이디 있음: " + user.getUserId());
        System.out.println("✅ 비밀번호 해시: " + user.getPassword());

        return user;
    }

    public User createUser(SignupRequest request, PasswordEncoder passwordEncoder){
        User user = new User();

        String password= passwordEncoder.encode(request.getPassword());
        user.setUserId(request.getUserId());
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
