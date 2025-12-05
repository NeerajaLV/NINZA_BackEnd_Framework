package com.ninza.hrm.api.genericutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * This class contains generic methods to use property file
 * @author neera
 */
public class PropertyFileUtility {
	public String getDataFromPropertiesFile(String key) throws IOException
	{
		FileInputStream fis = new FileInputStream("./EnvConfigData/envConfigData.properties");
		Properties p = new Properties();
		p.load(fis);
		String data = p.getProperty(key);
		
		return data;
	}
}
