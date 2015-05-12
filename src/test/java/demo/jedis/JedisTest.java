package demo.jedis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class JedisTest {
	static Jedis jedis = null;
	static {
		Properties ps = new Properties();
		InputStream inStream = JedisTest.class.getClassLoader()
				.getResourceAsStream("redis.config");

		try {
			ps.load(inStream);
			jedis = new Jedis(ps.getProperty("redis.host"),
					Integer.valueOf(ps.getProperty("redis.port")));
			jedis.auth(ps.getProperty("redis.pass"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSet() throws IOException {
		String keys = "foo";
		// 存数据

		
		jedis.set(keys, "snowolf");
		// 取数据
		String value = jedis.get(keys);

		System.out.println(value);
		jedis.del("foo");
	}

	@Test
	public void testHSet() throws IOException {
		jedis.hset("car:1", "name", "BMW");
		jedis.hset("car:1", "price", "1000000");
		System.out.println(jedis.hget("car:1", "name"));
		jedis.hdel("car:1", "name","price");
	}

	@Test
	public void testHGet() throws IOException {
//		jedis.auth("gome1234");
		System.out.println(jedis.hget("123", "xc"));
	}
}
