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
	
	/*
	 * 只會出現一組帳號密碼(每個帳戶只會出現一次)
	 */
	@Override
	public Map<String, Object> checkAccount(String name, String password) throws Exception {
		List<Map<String, Object>> accounts = accountDao.getAccountByName(name, password);
		if(!accounts.isEmpty()) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("Name", name);
			result.put("Password", password);
			result.put("Authorise", accounts.get(0).get("auth"));
			
			return result;
		}
		return null;
	}
}
