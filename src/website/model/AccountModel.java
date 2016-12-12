package website.model;

import java.util.Map;

public interface AccountModel {
	
	public Map<String, Object> checkAccount(String name, String password) throws Exception;
}
