package website.model.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import website.dao.AccountDao;
import website.model.AccountModel;

@Service("accountModel")
public class AccountModelImpl implements AccountModel{
	private AccountDao accountDao;
	
	@Resource(name = "accountDao")
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	
	// 檢查使用者登入帳號密碼
	@Override
	public Map<String, Object> checkAccount(String name, String password) throws Exception {
		List<Map<String, Object>> accounts = accountDao.getAccountByName(name, password); // 一個名字可能有多組不同的密碼
		
		for(Map<String, Object> user : accounts) {
			if(user.get("pass").equals(password)) {
				Map<String, Object> userAccount = new HashMap<String, Object>();
				userAccount.put("Name", name);
				userAccount.put("Password", user.get("pass"));
				userAccount.put("Authorise", user.get("auth"));
				return userAccount;
			}
		}
		
		return null; // 如果accounts裡面找不到一個符合password的map，代表使用者的密碼輸入錯誤
	}
}
