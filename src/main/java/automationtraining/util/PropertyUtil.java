package automationtraining.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyUtil {

	private static Properties properties=null;
	private static final Logger logger=LogManager.getLogger(PropertyUtil.class);
	
	private PropertyUtil() {
	}
	
	public static Properties getProperties() {
		if(properties==null) {
			properties=new Properties();
		}
		return properties;
	}
	
	public static void loadProperties() throws Exception {
		getProperties();
		String rootDiractory=System.getProperty("user.dir");
		properties.load(new FileInputStream(new File(rootDiractory+"\\src\\test\\resources\\config\\application.properties")));
		logger.info("Properties file loaded successfully");
	}
	
	public static String getProperty(String key) {
		return properties.getProperty(key,"");
	}
}
