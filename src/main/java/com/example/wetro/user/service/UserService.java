package com.example.wetro.user.service;

import com.example.wetro.user.dto.User;

public interface UserService {


    public boolean isExistId(String email);
    public boolean isExistEmail(String email);

    public User save(User user);

}
