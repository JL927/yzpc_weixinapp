package com.yzpc.yzpc_weixinapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author wq
 * @description
 * @date 2025/4/10 16:31:57
 */
@SpringBootTest
public class RedisTest {
    @Resource
    public StringRedisTemplate stringRedisTemplate;

    @Test
    public void testlink(){
        stringRedisTemplate.opsForValue().set("name","wq",60+ new Random().nextInt(60), TimeUnit.SECONDS);
        String name = stringRedisTemplate.opsForValue().get("name");
        System.out.println(name);
    }
}
