package com.kangyonggan.server.biz.cache;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author kangyonggan
 * @since 2016/10/16
 */
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {

    private JedisConnectionFactory jedisConnectionFactory;

    private RedisTemplate redisTemplate;

    private RedisCacheManager redisCacheManager;

    public RedisCacheConfig(JedisConnectionFactory jedisConnectionFactory, RedisTemplate redisTemplate, RedisCacheManager redisCacheManager) {
        this.jedisConnectionFactory = jedisConnectionFactory;
        this.redisTemplate = redisTemplate;
        this.redisCacheManager = redisCacheManager;
    }

}
