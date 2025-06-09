package com.reviewboy.domain.users.service;


import com.reviewboy.domain.users.entity.User;
import com.reviewboy.domain.users.models.SignupRequest;
import com.reviewboy.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void signup(SignupRequest signupRequest) {
        if(userRepository.existsByUsername(signupRequest.getUsername())){
           throw new IllegalArgumentException("이미 사용중인 아이디입니다.");
        }

        if(userRepository.existsByEmail(signupRequest.getEmail())){
            throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
        }

        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        User savedUser = userRepository.save(user);

        log.info(savedUser.toString() + " 회원가입 저장 완료");

    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
