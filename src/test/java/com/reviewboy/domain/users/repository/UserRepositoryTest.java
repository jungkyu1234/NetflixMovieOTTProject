package com.reviewboy.domain.users.repository;

import com.reviewboy.domain.users.entity.User;
import com.reviewboy.domain.users.repository.UserRepository;
import com.reviewboy.domain.users.roles.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
@ActiveProfiles("stage")
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 생성 테스트입니다.")
    public void createUsers(){
        User user = new User();
        user.setName("김지은");
        user.setUsername("jinny");
        user.setPassword("$2a$10$uX6m1tqJWd0qvGaN/9n5xOaFb0jGyYUJnB7hW3T9ZlPDI7UkeAzZm");
        user.setRoles(Role.ROLE_USER);
        user.setEmail("jieun@naver.com");
        user.setEnabled(true);
        userRepository.save(user);

        System.out.println(userRepository.findAll());
    }
}
