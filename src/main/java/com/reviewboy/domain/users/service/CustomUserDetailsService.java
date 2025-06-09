package com.reviewboy.domain.users.service;

import com.reviewboy.domain.users.cache.UserCache;
import com.reviewboy.domain.users.cache.UserCacheRepository;
import com.reviewboy.domain.users.entity.User;
import com.reviewboy.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserCacheRepository userCacheRepository;

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("username으로 userdetails를 조회");
        log.info("1차 cache 조회");
        /**
        UserCache userCache = userCacheRepository.findByUsername(username)
                .orElseGet(()->{
                    log.info("1차 cache 조회 MISS");
                    User user = userRepository.findByUsername(username);

                    if(user == null){
                        log.info("2차 db 조회 MISS");
                        return null;
                    }
                    UserCache userCacheFromDB = new UserCache();
                    userCacheFromDB.setUsername(user.getUsername());
                    userCacheFromDB.setPassword(user.getPassword());
                    userCacheFromDB.setUserId(user.getId());

                    log.info("1차 cache 에 저장 ");
                    userCacheRepository.save(userCacheFromDB);
                    return userCacheFromDB;
                });
        **/

        User userCache = userRepository.findByUsername(username);

        User user = new User();
        user.setUsername(userCache.getUsername());
        user.setPassword(userCache.getPassword());
        user.setId(userCache.getId());
        CustomUserDetails customUserDetails = new CustomUserDetails();
        System.out.println(user.toString());
        customUserDetails.setUser(user);
        return customUserDetails;
    }
}
