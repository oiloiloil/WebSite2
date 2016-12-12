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
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void initialConnect() {
		Connection conn = null;
		Statement stat = null;
	}
	
	//取得user資料，可以自行編寫修改，請使用JdbcTemplate做操作
	@Override
	public List<Map<String, Object>> getAccountByName(String name, String password) throws Exception {
		
		return null;
		
	}
	
	public void select() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet result = null;
		String sqlCmd = "";
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			stmt.executeQuery(sqlCmd);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // 
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} // 
			
		}
	}
}
