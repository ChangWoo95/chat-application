package me.changwoo.authserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisProvider {
    private final StringRedisTemplate stringRedisTemplate;

    public void setRedis(String key, String value) {
        ValueOperations<String, String> vop = stringRedisTemplate.opsForValue();
        vop.set(key, value);
    }
}
