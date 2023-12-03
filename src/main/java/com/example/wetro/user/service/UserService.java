package com.example.wetro.user.service;

import com.example.wetro.user.dto.User;
import com.example.wetro.user.dto.UserLoginDto;

import java.util.Optional;

public interface UserService {

    public Optional<User> findByUserid(String userid);

    public boolean isExistId(String email);
    public boolean isExistEmail(String email);

    public User save(User user);

    public Optional<User> login(UserLoginDto dto);

}
