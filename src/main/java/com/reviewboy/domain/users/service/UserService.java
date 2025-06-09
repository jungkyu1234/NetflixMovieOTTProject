package com.reviewboy.domain.users.service;

import com.reviewboy.domain.users.entity.User;
import com.reviewboy.domain.users.models.SignupRequest;

/**
 * User 서비스
 * 로그인, 회원가입 등의 서비스 처리
 */
public interface UserService {
    /**
     * 회원가입 메서드
     * @param signupRequest
     **/
    void signup(SignupRequest signupRequest);

    /**
     * 유저 찾기
     * @param username
     * @return
     */
    public User findByUsername(String username);

}
