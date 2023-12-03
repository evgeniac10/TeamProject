package com.example.wetro.user.service;

import com.example.wetro.user.dto.User;
import com.example.wetro.user.dto.UserLoginDto;
import com.example.wetro.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public Optional<User> findByUserid(String userid) {
        Optional<User> byUserid = userRepository.findByUserid(userid);
        return  byUserid;
    }

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

}
