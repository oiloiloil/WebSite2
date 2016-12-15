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
	
	
	//取得user資料，可以自行編寫修改，請使用JdbcTemplate做操作
	/*
	 * DataSource 是個資料來源，可用來取得資料庫連線 java.sql.Connection 的實作物件，
	 * 如果資料庫檔案不存在，在 setUrl 時就會建立新的檔案。在 Spring 的 JDBC 的封裝中，
	 * 最常使用的是 JdbcTemplate，其封裝了 JDBC 使用過程中可共用的樣版流程，
	 * 建構 JdbcTemplate 時，需要的正是一個 DataSource 實作物件。
	 * queryForObject(cqlCmd, 傳入值, 回傳值的類型)，
	 * 		Ex. String name = (String) jdbcTemplate.queryForObject(
                "SELECT name FROM USER WHERE id = ?",
                 new Object[] {id},
                 java.lang.String.class);
	 * 如果要回傳多筆資料，可以使用queryForList()，Ex. "Select * From account"
	 */
	@Override
	public List<Map<String, Object>> getAccountByName(String name, String password) throws Exception {
		List<Map<String, Object>> accounts = null;
		// 取出的accounts既為name這位user的所有可能密碼的list，只取出pass和auth這兩個欄位
		String sqlCmd = parseXml.getSqlByName("Account.checkAccount");
		accounts = jdbcTemplate.queryForList(sqlCmd, name);
		
		return accounts;
	}
	
}
