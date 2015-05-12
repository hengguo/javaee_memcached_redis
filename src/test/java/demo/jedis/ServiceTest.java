package demo.jedis;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.*;

import demo.jedis.service.AnswerService;
import demo.jedis.service.TopicService;
import demo.jedis.service.UserService;

public class ServiceTest {
	private ApplicationContext app;
	@Before
	public void before() throws Exception {
		app = new ClassPathXmlApplicationContext("spring-config.xml");
		userService =(UserService)app.getBean("userService");
		topicService =(TopicService)app.getBean("topicService");
		answerService =(AnswerService)app.getBean("answerService");
	}
	
	@Resource
	private UserService userService;
	@Resource
	private TopicService topicService;
	@Resource
	private AnswerService answerService;
	
	@Test
	public void insertALL(){
		Long userSize = 2L;
		Long topicSize = 2L;
		Long answerSize = 2L;
		for (int i = 1; i <= userSize.intValue(); i++) {
			User u = new User();
			u.setUid("uid:"+i);
			u.setName("Name:"+i);
			userService.put(u);
			for (int j = 1; j <= topicSize.intValue(); j++) {
				Topic topic = new Topic();
				topic.setId("topicid:"+j+":"+u.getKey());
				topic.setName("topicName"+j+u.getKey());
				topicService.put(topic);
				System.out.println("SAVING topic["+topic.getId()+"]");

				for (int k = 1; k <= answerSize.intValue(); k++) {
					Answer answer = new Answer();
					answer.setId("answerid:"+k+":"+topic.getId());
					answer.setContent("answerContent"+k);
					answerService.addUserAnswer(u, answer, topic);
				}
			}
		}
		
	}
	
	@Test
	public void getAll(){
		Long count = 0l;
		Long userSize = 2L;
		Long topicSize = 2L;
		Long answerSize = 2L;
		for (int i = 1; i <= userSize.intValue(); i++) {
			StringBuffer sb = new StringBuffer();
			User u = new User();
			u.setUid("uid:"+i);
			String name = userService.get(u);
			count++;
			u.setName(name);
			sb.append(""+u.toString()+">>>>>>>>>>>>");
			sb.append("\n");

			for (int j = 1; j <= topicSize.intValue(); j++) {
				Topic topic = new Topic();
				if(u !=null){
					topic.setId("topicid:"+j+":"+u.getKey());
					name = topicService.get(topic);
					count++;

					topic.setName(name);
					sb.append("\n");
					for (int k = 1; k <= answerSize.intValue(); k++) {
						sb.append(""+topic.toString()+">>>>>>>>>>>>");
						Answer answer = new Answer();
						answer.setId("answerid:"+k+":"+topic.getId());
						String content = answerService.get(answer);
						count++;

						answer.setContent(content);
						sb.append(answer);
					}
					sb.append("<<<<<<<<<<<<<<<<<");

				}
				sb.append("\n");
			}
			sb.append("<<<<<<<<<<<<<<<<<");

			sb.append("================================================\n");
			System.out.println(sb.toString());
		}
		System.err.println(count);
	}
	
	@Test
	public void delAll(){
		Long userSize = 10L;
		Long topicSize = 10L;
		Long answerSize = 10L;
		for (int i = 1; i <= userSize.intValue(); i++) {
			User u = new User();
			u.setUid("uid:"+i);
			u.setName("Name:"+i);
			userService.delete(u);
			for (int j = 1; j <= topicSize.intValue(); j++) {
				Topic topic = new Topic();
				topic.setId("topicid:"+j+":"+u.getKey());
				topic.setName("topicName"+j+u.getKey());
				topicService.delete(topic);
				for (int k = 1; k <= answerSize.intValue(); k++) {
					Answer answer = new Answer();
					answer.setId("answerid:"+k);
					answer.setContent("answerContent"+k);
					answerService.deleteUserAnswer(u, answer, topic);
				}
			}
			System.out.println("deleting user["+i+"]");
		}
		
	}

}
