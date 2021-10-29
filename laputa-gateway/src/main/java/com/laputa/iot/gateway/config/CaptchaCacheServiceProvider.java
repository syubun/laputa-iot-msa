package com.laputa.iot.gateway.config;

import java.util.concurrent.TimeUnit;

import com.anji.captcha.service.CaptchaCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author sommer.jiang
 * @date 2020/8/27
 * <p>
 * 验证码 缓存提供支持集群,需要通过SPI
 */
public class CaptchaCacheServiceProvider implements CaptchaCacheService {

	private static final String REDIS = "redis";

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public void set(String key, String value, long expiresInSeconds) {
		stringRedisTemplate.opsForValue().set(key, value, expiresInSeconds, TimeUnit.SECONDS);
	}

	@Override
	public boolean exists(String key) {
		return stringRedisTemplate.hasKey(key);
	}

	@Override
	public void delete(String key) {
		stringRedisTemplate.delete(key);
	}

	@Override
	public String get(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	@Override
	public String type() {
		return REDIS;
	}

}
