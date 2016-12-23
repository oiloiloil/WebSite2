package website.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import website.common.ParseXml;
import website.dao.AccountDao;
import website.model.UserInfo;

@Service("accountDao")
public class AccountDaoImpl implements AccountDao{
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private ParseXml parseXml;
	
	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		parseXml = new ParseXml();
	}
	
	
	// 取得user資料，可以自行編寫修改，請使用JdbcTemplate做操作
	@Override
	public List<Map<String, Object>> getAccountByName(String name, String password) throws Exception {
		List<Map<String, Object>> accounts = null;
		String sqlCmd = parseXml.getSqlByName("Account.checkAccount");
		accounts = jdbcTemplate.queryForList(sqlCmd, name); // 取出的accounts為這位user的所有可能密碼的list(傳入name)，只取出pass和auth這兩個欄位
		
		return accounts;
	}

	// 新增一筆使用者資料
	@Override
	public void createUser(UserInfo user) throws Exception {
		String sqlCmd = parseXml.getSqlByName("Account.createAccount");
		jdbcTemplate.update(sqlCmd, user.getName(), user.getPasswd(), user.getPhone(), user.getEmail(), user.getBirth());
	}
	
}
