package com.reviewboy;

import com.reviewboy.batch.MovieBatchProcessor;
import com.reviewboy.openapi.movie.MovieOpenApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
//@Component
public class AppRunner implements ApplicationRunner {

    private final MovieOpenApiService movieOpenApiService;
    private final MovieBatchProcessor movieBatchProcessor;
    private final RedisTemplate<?,?> redisTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        movieOpenApiService.retrieveAllMovies("2025", 4, 100);
        movieBatchProcessor.runMonthlyTask();
        System.out.println(redisTemplate.getKeySerializer().getClass().getName());
        System.out.println(redisTemplate.getValueSerializer().getClass().getName());
    }
}
