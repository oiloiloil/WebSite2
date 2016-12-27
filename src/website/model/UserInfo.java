package website.model;

import java.text.SimpleDateFormat;

public class UserInfo {
	private String name = "";
	private String passwd = "";
	private String phone = "";
	private String email = "";
	private String birth = "";
	
	public UserInfo() {
	}
	
	
	// 一次檢查所有的欄位是否正確，全部正確才會回傳true
	public boolean check() {
		if(!isNameCorrect())
			return false;
		if(!isPassCorrect())
			return false;
		if(!isPhoneCorrect())
			return false;
		if(!isEmailCorrect())
			return false;
		if(!isBirthCorrect())
			return false;
		
		return true;
	}
	
	public boolean isNameCorrect() {
		String nameRule = "^[a-zA-Z]+[a-zA-Z0-9_]*";
		return this.name.matches(nameRule);
	}
	public boolean isPassCorrect() { // pass: 6~12英數字
		String passRule = "[a-zA-Z0-9]{6,}";
		if(this.passwd.length() > 12)
			return false;
		return this.passwd.matches(passRule);
	}
	public boolean isPhoneCorrect() {
		String phoneRule = "^(09)[0-9]{8}"; // 09開頭，09XXXXXXXX
		return this.phone.matches(phoneRule);
	}
	public boolean isEmailCorrect() {
		String emailRule = "^[A-Za-z0-9_]+(([.][A-Za-z0-9]+)|([-][A-Za-z0-9]+))*@[A-Za-z0-9]+(([.]|[-])([A-Za-z0-9]+))*[.][A-Za-z]+$";
		return this.email.matches(emailRule);
	}
	public boolean isBirthCorrect() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		try {
			format.setLenient(false);
			format.parse(this.birth);
		} catch(Exception e) {
			return false;
		}
		return true;
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
