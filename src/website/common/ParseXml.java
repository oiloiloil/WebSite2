package website.common;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ParseXml {
	private static String SQLFileName = "Sql.xml"; // getclassloader.getResourceAsStream  // getCurrentThread 
	private static Document document = null;
	
	public static String getSqlByName (String sqlName) throws Exception {
		SAXReader reader = new SAXReader();
		File file = new File(getBasePath() + SQLFileName);
		document = reader.read(file);
		
		return getXMLFileElement(sqlName, document.getRootElement());
	}
	
	// 遞迴尋找符合str的tag的內容，使用tree walk
	private static String getXMLFileElement(String str, Element e) {
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
		String path = ParseXml.class.getResource("/").getPath();
		path = path.replaceFirst("/", "");
		//path = path.replaceFirst("WEB-INF/classes/", "");
		return path;
	}
	
}
