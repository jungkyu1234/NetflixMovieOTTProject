package com.reviewboy.domain.users.cache;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;

@Getter @Setter
@RedisHash(value = "users", timeToLive = 600)
public class UserCache {

    @Id
    private String username;

    private String password;

    private Long userId;

    @TimeToLive(unit = TimeUnit.SECONDS)
    private Long timeToLive = null;

}
