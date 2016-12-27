package website.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserInfoTest {
	private UserInfo user;

	@Before
	public void setUp() throws Exception {
		user = new UserInfo(); // 先放入正確的值，然後在每個test case 裡再去針對其中一項修改
		user.setName("oil");
		user.setPasswd("123456");
		user.setPhone("0920800902");
		user.setEmail("oliver.ou@hy-tech.com.tw");
		user.setBirth("1991/09/02");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testName_invalidInput() {
		user.setName("*12345");
		assertFalse(user.isNameCorrect());
	}
	
	// 全輸入符號
	@Test
	public void testName_invalidInputWithSymbol() {
		user.setName("*-_[](");
		assertFalse(user.isNameCorrect());
	}

	@Test
	public void testName_overMaxLength() {
		user.setName("123456789abcd");
		assertFalse(user.isNameCorrect());
	}
	
	@Test
	public void testPass_notEnoughLength() {
		user.setPasswd("12345");
		assertFalse(user.isPassCorrect());
	}
	
	@Test
	public void testPass_overLength() {
		user.setPasswd("1234567890123"); // 13個數字
		assertFalse(user.isPassCorrect());
	}
	
	@Test
	public void testPass_validLength() {
		user.setPasswd("12345678");
		assertTrue(user.isPassCorrect());
	}
	
	// 全輸入符號
	@Test
	public void testPass_invalidInputWithSymbol() {
		user.setPasswd("*_-.+%");
		assertFalse(user.isPassCorrect());
	}
	
	@Test
	public void testPhone_notEnoughPhoneNumber() {
		user.setPhone("1");
		assertFalse(user.isPhoneCorrect());
	}
	
	@Test
	public void testPhone_overMaxLength() {
		user.setPhone("01001235679");
		assertFalse(user.isPhoneCorrect());
	}
	
	@Test
	public void testPhone_invalidInput() {
		user.setPhone("0200123456");
		assertFalse(user.isPhoneCorrect());
	}
	
	@Test
	public void testEmail_invalidInput1() {
		user.setEmail("abc.@abc.abc");
		assertFalse(user.isEmailCorrect());
	}
	
	@Test
	public void testEmail_invalidInput2() {
		user.setEmail("cde-cde@fgh..fgh");
		assertFalse(user.isEmailCorrect());
	}
	
	@Test
	public void testEmail_invalidInput3() {
		user.setEmail("dfg.dfg@");
		assertFalse(user.isEmailCorrect());
	}
	
	@Test
	public void testBirth_notExistDate() {
		user.setBirth("2016/2/30");
		assertFalse(user.isBirthCorrect());
	}
	
	@Test
	public void testName_validInput() {
		assertTrue(user.isNameCorrect());
	}
	
	@Test
	public void testPass_validInput() {
		assertTrue(user.isPassCorrect());
	}
	
	@Test
	public void testPhone_validInput() {
		assertTrue(user.isPhoneCorrect());
	}
	
	@Test
	public void testEmail_validInput() {
		assertTrue(user.isEmailCorrect());
	}
	
	@Test
	public void testBirth_validInput() {
		assertTrue(user.isBirthCorrect());
	}
	
	@Test
	public void testCheck() {
		assertTrue(user.check());
	}
}
