package me.dev.service;

import me.dev.dto.payload.request.SignupRequest;
import me.dev.entity.User;
import me.dev.repository.UserReposioty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserReposioty userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        System.out.println("email: " + email);
        System.out.println("Member email: " + user.getEmail());
        System.out.println("encodedPassword: " + user.getPassword());
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }

        return user;
    }

    public User createUser(SignupRequest request, PasswordEncoder passwordEncoder){
        User user = new User();

        String password= passwordEncoder.encode(request.getPassword());
        user.setPassword(password); // 암호화 필요!
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


}
