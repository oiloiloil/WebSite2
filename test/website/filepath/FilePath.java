package website.filepath;

import website.common.ParseXmlTest;

public class FilePath {
	public FilePath() {
		
	}
	
	public static String getBasePath() {
		String path = ParseXmlTest.class.getResource("/").getPath();
		path = path.replaceFirst("/", "");
		path = path.replaceFirst("WEB-INF/classes/", "");
		return path;
	}
}
