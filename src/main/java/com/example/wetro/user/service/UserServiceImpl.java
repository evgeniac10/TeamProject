package com.example.wetro.user.service;

import com.example.wetro.user.dto.User;
import com.example.wetro.user.dto.UserLoginDto;
import com.example.wetro.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public boolean isExistId(String userid) {
        Optional<User> existingUser = userRepository.findByUserid(userid);
        return existingUser.isPresent();
    }

    @Override
    public boolean isExistEmail(String userid) {
        Optional<User> existingUser = userRepository.findByemail(userid);
        return existingUser.isPresent();
    }


    public User save(User user){
        return userRepository.save(user);
    }

    public Optional<User> login(UserLoginDto dto) {
        log.info("login service");
        // 사용자 아이디로 사용자 조회
        Optional<User> loginUser = userRepository.findByUserid(dto.getUserid());
        log.info("로그인을 위한 사용자 조회 = {}",loginUser.get());
        // 사용자가 존재하고, 비밀번호가 일치하면 로그인 성공
        if (loginUser.isPresent() && passwordEncoder.matches(dto.getPassword(), loginUser.get().getPassword())) {
            User user = loginUser.get();
            String storedEncodedPassword = user.getPassword();
            if (passwordEncoder.matches(dto.getPassword(), storedEncodedPassword)) {
                log.info("loginUser = {}",dto.getUserid());
                return loginUser;
            } else {
                // 비밀번호 불일치
                log.info("비밀번호 불일치");
                return Optional.empty();
            }
        }
        // 로그인 실패
        return Optional.empty();
    }

    private boolean verifyPassword(String inputPassword, String hashedPassword) {
        // 비밀번호 해싱 및 비교 로직 구현
        // 실제로는 해시 라이브러리를 사용하거나 스프링 시큐리티의 기능을 활용하는 것이 좋습니다.
        // 여기서는 단순 예시로 문자열 비교를 사용하고 있습니다.
        return inputPassword.equals(hashedPassword);
    }

}
