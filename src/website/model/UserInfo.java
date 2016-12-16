package website.model;

public class UserInfo {
	private String name;
	private String passwd;
	private String phone;
	private String email;
	private String birth;
	
	public UserInfo() {
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPasswd() {
		return this.passwd;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone() {
		return this.phone;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return this.email;
	}
	
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getBirth() {
		return this.birth;
	}
}
