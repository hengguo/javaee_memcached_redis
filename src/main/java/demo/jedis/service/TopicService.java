package demo.jedis.service;

import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.*;

import demo.jedis.Topic;

@Service("topicService")
public class TopicService {

	@Resource(name="stringRedisTemplate")
	private StringRedisTemplate redisTemplate;

	public void put(Topic topic) {
		redisTemplate.opsForHash().put(topic.getObjectKey(), topic.getId(), topic.getName());
	}

	public void delete(Topic topic) {
		redisTemplate.opsForHash().delete(topic.getObjectKey(), topic.getKey());
	}

	public String get(Topic topic) {
		return (String)redisTemplate.opsForHash().get(topic.getObjectKey(), topic.getKey());
	}
}