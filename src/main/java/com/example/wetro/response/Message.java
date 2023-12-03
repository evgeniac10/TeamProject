package com.example.wetro.response;

import lombok.Getter;

@Getter
public class Message {

    public static final String SUCCESS_UES_ID = "아이디 사용이 가능합니다.";
    public static final String FAIL_USE_ID = "중복된 아이디 입니다.";
    public static final String FAIL_USE_EMAIL = "중복된 이메일 입니다.";

    public static final String SUCCESS_TO_SEND_VERI = "이메일 인증번호 발송 성공";
    public static final String SUCCESS_TO_VERI_CODE = "이메일 인증 성공";
    public static final String FAIL_TO_VERI_CODE = "이메일 인증 실패";

    public static final String SUCCESS_TO_SIGN = "회원가입 성공";
    public static final String SUCCESS_TO_FAIL = "회원가입 실패";
    public static final String VALID_INPUT_REQUIRED = "올바른 값을 입력해주세요";

    public static final String SUCCESS_TO_LOGIN = "로그인 성공";
    public static final String FAIL_TO_LOGIN = "로그인 실패";
    public static final String SUCCESS_TO_LOGOUT = "로그아웃 성공";
    public static final String FAIL_TO_LOGOUT = "로그아웃 실패";

    public static final String SUCCESS_TO_INFO = "경로 계산 완료";

    public static final String SUCCESS_TO_BOOKMARK = "즐겨찾기 등록 완료";
    public static final String SUCCESS_TO_EDIT_BOOKMARK = "즐겨찾기 수정 완료";
    public static final String FAIL_TO_EDIT_BOOKMARK = "즐겨찾기 수정 실패";

}
