package demo.jedis.service;

import javax.annotation.Resource;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import demo.jedis.Answer;
import demo.jedis.Topic;
import demo.jedis.User;

@Service("answerService")
public class AnswerService {
	private String getUserAnswerKey(User u) {
		return "USER" + ":" + u.getKey() + ":"+"ANSWERS";
	}

	private String getTopicAnswerKey(Topic t) {
		return "TOPIC" + ":" + t.getKey() +":"+ "ANSWERS";
	}

	@Resource(name = "stringRedisTemplate")
	private StringRedisTemplate redisTemplate;

	/**
	 * 根据问题添加 相关的用户及话题
	 * @param user
	 * @param answer
	 * @param topic
	 */
	public void addUserAnswer(User user, Answer answer, Topic topic) {
		redisTemplate.multi();
		redisTemplate.opsForHash().put(answer.getObjectKey(), answer.getId(),
				answer.getContent());
		redisTemplate.opsForSet().add(getUserAnswerKey(user), answer.getId());
		redisTemplate.opsForSet().add(getTopicAnswerKey(topic), answer.getId());
//		redisTemplate.exec();

	}

	public void delete(Answer answer) {
		redisTemplate.opsForHash().delete(answer.getObjectKey(),
				answer.getKey());
	}
	
	public void deleteUserAnswer(User user, Answer answer, Topic topic) {
		redisTemplate.opsForHash().delete(answer.getObjectKey(),
				answer.getKey());
		redisTemplate.opsForSet().remove(getUserAnswerKey(user),answer.getId());
		redisTemplate.opsForSet().remove(getTopicAnswerKey(topic), answer.getId());
	}

	public String get(Answer answer) {
		return (String) redisTemplate.opsForHash().get(answer.getObjectKey(),
				answer.getKey());
	}
}