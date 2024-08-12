package com.banurr.pet_project.configuration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
@Slf4j
public class RedisCacheClean implements CommandLineRunner {

    private final StringRedisTemplate redisTemplate;


    @Override
    public void run(String... args) throws Exception {
        Objects.requireNonNull(redisTemplate.getConnectionFactory()).getConnection().flushDb();
        log.info("Redis cache was cleaned");
    }
}
