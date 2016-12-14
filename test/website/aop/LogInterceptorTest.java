package website.aop;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LogInterceptorTest {
	protected Logger logger;
	protected LogInterceptor log;
	@Before
	public void setUp() throws Exception {
		logger = Logger.getLogger(LogInterceptorTest.class);
		log = new LogInterceptor();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Throwable {
		fail("Not yet implemented");
	}
	
	

}
