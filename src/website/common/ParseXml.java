package website.common;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sun.media.jfxmedia.logging.Logger;

public class ParseXml {
	private static String SQLFileName = "Sql.xml"; 
	private static Document document = null;
	
	public static String getSqlByName (String sqlName) throws Exception {
		SAXReader reader = new SAXReader();
		File file = new File(getBasePath() + SQLFileName);
		document = reader.read(file);
		
		return getXMLFileElement(sqlName, document.getRootElement());
	}
	
	// 遞迴尋找符合str的tag的內容，使用tree walk
	private static String getXMLFileElement(String str, Element e) {		
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
		return path;
	}
	
}
