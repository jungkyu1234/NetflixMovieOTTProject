package com.reviewboy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@ConfigurationPropertiesScan
@SpringBootApplication
@EnableScheduling
public class ReviewboyappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReviewboyappApplication.class, args);
        System.out.println(new BCryptPasswordEncoder().encode("1234"));
    }

}
