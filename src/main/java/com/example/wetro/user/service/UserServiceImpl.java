package com.example.wetro.user.service;

import com.example.wetro.user.dto.User;
import com.example.wetro.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;


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

}
