package com.ninza.hrm.api.generic.baseclass;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.ninza.hrm.api.generic.pomclass.utility.LoginPage;
import com.ninza.hrm.api.generic.pomclass.utility.ProjectsPage;
import com.ninza.hrm.api.genericutility.DatabaseUtility;
import com.ninza.hrm.api.genericutility.ExcelFileUtility;
import com.ninza.hrm.api.genericutility.JavaFileUtility;
import com.ninza.hrm.api.genericutility.PropertyFileUtility;
import com.ninza.hrm.api.genericutility.WebDriverUtility;

public class BaseClass {
	// create object
	public PropertyFileUtility PUtil = new PropertyFileUtility();
	public WebDriverUtility WUtil = new WebDriverUtility();
	public ExcelFileUtility EUtil = new ExcelFileUtility();
	public JavaFileUtility JUtil = new JavaFileUtility();
	public DatabaseUtility DUtil = new DatabaseUtility();
	public WebDriver driver=null;
		
		@Parameters("BROWSER")
		@BeforeClass(alwaysRun=true)
		public void configBC( /* String browser */ ) throws IOException
		{
			final Map<String, Object> chromePrefs = new HashMap<>();
			chromePrefs.put("credentials_enable_service", false);
			chromePrefs.put("profile.password_manager_enabled", false);
			chromePrefs.put("profile.password_manager_leak_detection", false);
			final ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("prefs", chromePrefs);
			System.out.println("===Launch Browser===");
			String BROWSER = System.getProperty("browser", PUtil.getDataFromPropertiesFile("BRWOSER"));
	//		String BROWSER = browser;
			if (BROWSER.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver(chromeOptions);
			} else if (BROWSER.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			} else if (BROWSER.equalsIgnoreCase("edge")) {
				driver = new EdgeDriver();
			} else {
				driver = new ChromeDriver();
			}
		}
		@BeforeMethod(alwaysRun=true)
		public void configBM() throws Throwable
		{
			System.out.println("===Login===");
			String USERNAME = System.getProperty("username", PUtil.getDataFromPropertiesFile("USERNAME"));
			String PASSWORD = System.getProperty("password", PUtil.getDataFromPropertiesFile("PASSWORD"));
			LoginPage LPage=new LoginPage(driver);
			LPage.loginToApp(driver, USERNAME, PASSWORD);
		}
		@AfterMethod(alwaysRun=true)
		public void configAM() throws Throwable
		{
			Thread.sleep(2000);
			System.out.println("===Logout===");
			ProjectsPage PPage=new ProjectsPage(driver);
			PPage.logout(driver);
		}
		@AfterClass(alwaysRun=true)
		public void configAC()
		{
			System.out.println("===Close Browser===");
			driver.quit();
		}
}
