package website.dao;

import java.util.List;
import java.util.Map;

import website.model.UserInfo;

public interface AccountDao {
	public List<Map<String, Object>> getAccountByName(String name, String password) throws Exception;
	public void createUser(UserInfo user) throws Exception;
	public boolean findUser(String acct, String pass) throws Exception;
}
