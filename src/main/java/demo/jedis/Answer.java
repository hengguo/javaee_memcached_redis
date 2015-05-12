package demo.jedis;

public class Answer {
	private String id;
	private String content;
	private static final String OBJECT_KEY = "ANSWER";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static String getObjectKey() {
		return OBJECT_KEY;
	}

	public Object getKey() {
		return getId();
	}
	
	public String toString(){
        return "Answer [id=" + id + ", content=" + content + "]";  
	}
}
