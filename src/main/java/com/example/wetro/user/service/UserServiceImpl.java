package com.example.wetro.user.service;

import com.example.wetro.user.dto.User;
import com.example.wetro.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isUserIdDuplicate(String userid) {
        Optional<User> existingUser = userRepository.findByUserid(userid);
        return existingUser.isPresent();
    }
    @Override
    public String generateAndSendVerificationCode(String email) {
        // 여기에는 이메일을 통한 인증번호 생성 및 발송 로직이 들어갑니다.
        // 실제로는 외부 API나 이메일 서비스를 사용하여 구현할 수 있습니다.
        // 이 예시에서는 간단하게 랜덤한 문자열을 생성하는 메서드를 사용합니다.
        String verificationCode = generateRandomCode();
        // 여기서 이메일 전송 로직을 추가해야 합니다.
        // sendEmail(email, "Verification Code", "Your verification code is: " + verificationCode);
        return verificationCode;
    }

    @Override
    public boolean verifyCode(String email, String code) {
        // 여기에는 이메일 주소와 사용자가 입력한 코드를 확인하는 로직이 들어갑니다.
        // 실제로는 데이터베이스에 저장된 코드와 비교하거나, 외부 API 호출 등이 필요할 수 있습니다.
        // 이 예시에서는 간단하게 코드가 "1234"인 경우에만 성공으로 간주합니다.
        return "1234".equals(code);
    }

    @Override
    public boolean signUp(String userid, String password, String email) {
        // 여기에는 사용자 등록 로직이 들어갑니다.
        // 패스워드 해싱, 데이터베이스에 사용자 정보 저장 등이 이루어져야 합니다.
        // 이 예시에서는 간단하게 사용자 정보를 생성하여 저장합니다.
        User newUser = new User(userid, password, email);
        userRepository.save(newUser);
        return true;  // 가입 성공을 의미하는 값을 반환합니다.
    }

    private String generateRandomCode() {
        // 간단한 랜덤 코드 생성 로직 (예시용)
        // 실제로는 더 안전한 방법으로 코드를 생성하는 것이 좋습니다.
        return String.valueOf((int) (Math.random() * 9000) + 1000);
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
