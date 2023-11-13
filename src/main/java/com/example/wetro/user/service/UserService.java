package com.example.wetro.user.service;

import com.example.wetro.user.dto.User;

public interface UserService {
    boolean isUserIdDuplicate(String userid);

    String generateAndSendVerificationCode(String email);

    boolean verifyCode(String email, String code);

    boolean signUp(String userid, String password, String email);

    public User saveUser(String userid , String password , String email);
    public User findUser(String userid, String password);
}
