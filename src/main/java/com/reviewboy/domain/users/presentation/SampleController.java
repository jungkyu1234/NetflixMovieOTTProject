package com.reviewboy.domain.users.presentation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api")
public class SampleController {

    @GetMapping("/product")
    public Map<String, String> getProduct(@AuthenticationPrincipal UserDetails userDetails){
        log.info(userDetails.getUsername() + "가 현재 상품을 조회중입니다.");
        return Collections.singletonMap("now-time", LocalDateTime.now().toString());
    }
}
