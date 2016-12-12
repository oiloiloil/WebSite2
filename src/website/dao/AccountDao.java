package website.dao;

import java.util.List;
import java.util.Map;

public interface AccountDao {
	public List<Map<String, Object>> getAccountByName(String name, String password) throws Exception;
}
