package website.common;

import static org.junit.Assert.*;

import java.io.File;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParseXmlTest {
	private String SQLFileName = "resources/Sql.xml";
	private Document document = null;
	
	@Before
	public void setUp() throws Exception {
		SAXReader reader = new SAXReader();
		document = reader.read(new File(SQLFileName));
	}

	@After
	public void tearDown() throws Exception {
		document = null;
	}

	@Test
	public void testGetXMLFile() {
		System.out.println("" + ParseXmlTest.class.getClassLoader().getResource("Sql.xml"));
		System.out.println("" + this.getClass().getClassLoader().getResource("Sql.xml"));
		System.out.println("" + getBasePath() + "Sql.xml");
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("Sql.xml");
		
		if(document == null)
			fail();
		assertTrue(true);
	}
	
	@Test
	public void testGetElementFromXML() {
		String tag = "Account.checkAccount";
		assertEquals("SELECT PASS, AUTH FROM ACCOUNT WHERE ACCT = ?", getXMLFileElement(tag, document.getRootElement()));
		
		tag = "Industry.queryIndustry";
		assertEquals("SELECT * FROM INDUSTRY LIMIT ?, ?", getXMLFileElement(tag, document.getRootElement()));
		
		tag = "Industry.getIndustryCount";
		assertEquals("SELECT COUNT(*) COUNT FROM INDUSTRY", getXMLFileElement(tag, document.getRootElement()));
		
		tag = "Industry.insertIndustry";
		assertEquals("INSERT INTO INDUSTRY(IND_NAME, IND_DESC) VALUES (?, ?)", getXMLFileElement(tag, document.getRootElement()));
		
		tag = "Industry.updateIndustry";
		assertEquals("UPDATE INDUSTRY SET IND_NAME = ?, IND_DESC = ? WHERE PK = ?", getXMLFileElement(tag, document.getRootElement()));
		
		tag = "Industry.deleteIndustry";
		assertEquals("DELETE FROM INDUSTRY WHERE PK = ?", getXMLFileElement(tag, document.getRootElement()));
	}
	
	@Test
	public void testGetSqlByName() {
		fail("Not yet implemented");
	}
	
	// 遞迴尋找符合str的tag的內容，使用tree walk
	private String getXMLFileElement(String str, Element e) {
		/*
		 * 每個element的tag name可以參考以下的e.getName()，而e.getTextTrim()則是取得這個tag裡面的text，
		 * Ex.<Account.checkAccount>SELECT PASS, AUTH FROM ACCOUNT WHERE ACCT = ?</Account.checkAccount>
		 * 這個element的.getTextTrim()就會回傳"SELECT PASS, AUTH FROM ACCOUNT WHERE ACCT = ?"
		 */
		// System.out.println("Tag: " + e.getName() + " E value: " + e.getTextTrim());
		
		if(e.getName().equals(str)) {
			return e.getTextTrim();
		}
		for(Element child : e.elements()) {
			if(getXMLFileElement(str, child) != null)
				return getXMLFileElement(str, child);
		}
		return null;
	}
	
	private static String getBasePath() {
		String path = ParseXmlTest.class.getResource("/").getPath();
		path = path.replaceFirst("/", "");
		path = path.replaceFirst("WEB-INF/classes/", "");
		return path;
	}

}
