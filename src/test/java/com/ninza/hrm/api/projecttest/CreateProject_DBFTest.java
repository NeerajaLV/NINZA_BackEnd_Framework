package com.ninza.hrm.api.projecttest;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ninza.hrm.api.generic.baseclass.BaseAPIClass;
import com.ninza.hrm.constants.endpoints.IEndPoints;

public class CreateProject_DBFTest extends BaseAPIClass {
	@Parameters("BROWSER")
	@Test
	public void createProjectTest(/* String browser */ ) throws Throwable {
		// DB
		int ranNum = JUtil.getRandomNumber();
		String PROJ_NAME = "PROJ_SC_N1_" + ranNum;
		String projectId = "PROJ_SC_I_" + ranNum;
		String createdOn = JUtil.getSysDateInDDMMYYYY();
		int createdStatus = DUtil.executeUpdate("insert into project values('" + projectId + "','MARK','" + createdOn
				+ "','" + PROJ_NAME + "','Created',0);");
		System.out.println(projectId + "\n" + createdOn + "\n" + PROJ_NAME + "\n" + createdStatus);
		boolean availableStatus = DUtil.executeQueryVerifyAndGetData(
				"select * from project where project_id='" + projectId + "';", 4, PROJ_NAME);
		Assert.assertTrue(availableStatus);
		// BE
		String PROJ_NAME_U = "PROJ_SC_N2_" + ranNum;
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("projectName", PROJ_NAME_U);
		given().spec(reqSpec).pathParam("projectId", projectId).body(jsonobj.toJSONString()).when()
				.put(IEndPoints.UpdateProj).then().spec(resSpec).assertThat().statusCode(200).log().all();
		// FE
		final Map<String, Object> chromePrefs = new HashMap<>();
		chromePrefs.put("credentials_enable_service", false);
		chromePrefs.put("profile.password_manager_enabled", false);
		chromePrefs.put("profile.password_manager_leak_detection", false);
		final ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("prefs", chromePrefs);
		System.out.println("===Launch Browser===");
		WebDriver driver;
		String BROWSER = System.getProperty("browser", PUtil.getDataFromPropertiesFile("BRWOSER"));
//				String BROWSER = browser;
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver(chromeOptions);
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		driver.get("http://49.249.28.218:8091/");
		driver.manage().window().maximize();

		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[.='Sign in']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[.='Projects']")).click();
		WUtil.selectByVisibleText(driver.findElement(By.xpath("//select[@class='form-control']")),
				"Search by Project Name");
		driver.findElement(By.xpath("//input[@placeholder='Search by Project Name']")).sendKeys(PROJ_NAME_U);
		
		try {
		boolean projectAvailabilityStatus=driver.findElement(By.xpath("//td[.='"+PROJ_NAME_U+"']")).isDisplayed();
		Assert.assertTrue(!projectAvailabilityStatus,"Project deletion not verified in GUI");
		System.out.println(projectAvailabilityStatus);
		}catch(Exception e) {}
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[local-name()='svg' and @class='svg-inline--fa fa-right-from-bracket ']"))
				.click();
		System.out.println("===Closing Browser===");
		driver.quit();
		// DB
//		boolean deletedStatus = false;
//		if(createdStatus)
//		{
//			deletedStatus=DUtil.executeQueryVerifyAndGetData("delete from project where project_name='"+projectName+"';", 4, projectName);
//		}
//		Assert.assertTrue(!deletedStatus,"Project delettion not verified in DB");
	}
}
