package demo.jedis;


public class User {

	private String uid;

	private String name;
	
	private String address;
	
    public static final String OBJECT_KEY = "USER";  

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public static String getObjectKey() {
		return OBJECT_KEY;
	}
	public String getKey(){
		return getUid();
	}
	public String toString() {  
        return "User [id=" + uid + ", name=" + name + "]";  
    } 
}