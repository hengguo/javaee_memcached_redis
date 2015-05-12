package demo.jedis.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.*;

import demo.jedis.User;

@Service("userService")
public class UserService {

//	@Resource
//	private RedisTemplate<String, User> redisTemplate;
	@Resource(name="stringRedisTemplate")
	private StringRedisTemplate redisTemplate;

	public void put(User user) {
		redisTemplate.opsForHash().put(User.getObjectKey(), user.getKey(), user.getName());
	}

	public void delete(User key) {
		redisTemplate.opsForHash().delete(User.getObjectKey(), key.getKey());
	}

	public String get(User key) {
		return (String) redisTemplate.opsForHash().get(User.getObjectKey(), key.getKey());
	}
}