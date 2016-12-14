package website.dao.impl;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import website.dao.AccountDao;

public class AccountDaoImplTest {
	protected AccountDao accountDao;
	
	@Before
	public void setUp() throws Exception {
		// 不使用xml的方式建立dataSource
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/demo?characterEncoding=utf-8");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");
		accountDao = new AccountDaoImpl();
		
		((AccountDaoImpl)accountDao).setDataSource(dataSource);
	}

	@After
	public void tearDown() throws Exception {
	}

	// 目前只用人工方式確認pass以及auth是否正確
	@Test
	public void testGetAccountByName() throws Exception {
		String name = "oil", pass = "123";
		List<Map<String, Object>> result = accountDao.getAccountByName(name, pass);
		
		for(Map<String, Object> item : result) {
			System.out.println("name: " + name);
			System.out.println("pass: " + item.get("pass"));
			System.out.println("auth: " + item.get("auth"));
		}
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
