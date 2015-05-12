package demo.jedis;

public class Topic {
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    public static final String OBJECT_KEY = "TOPIC";

	public static String getObjectKey() {
		return OBJECT_KEY;
	}

	public String getKey() {
		return getId();
	}  
	
	public String toString(){
        return "Topic [id=" + id + ", name=" + name + "]";  
	}

}
