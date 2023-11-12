package com.example.wetro.user.service;

import com.example.wetro.user.dto.User;
import com.example.wetro.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(String userid , String password , String email){
        User inputuser = new User();
        inputuser.setUserid(userid);
        inputuser.setPassword(password);
        inputuser.setEmail(email);
        return userRepository.save(inputuser);
    }
    public User findUser(String userid, String password) {
        return userRepository.findByUseridAndPassword(userid, password);
    }
}
