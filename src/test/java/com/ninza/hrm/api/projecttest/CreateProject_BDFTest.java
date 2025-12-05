package com.ninza.hrm.api.projecttest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.util.HashMap;
import java.util.Map;

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
import com.ninza.hrm.api.generic.pojoclass.utility.CreateProject_POJO;
import com.ninza.hrm.constants.endpoints.IEndPoints;

import io.restassured.response.Response;

public class CreateProject_BDFTest extends BaseAPIClass {
	@Parameters("BROWSER")
	@Test
	public void createProjectTest(/* String browser */ ) throws Throwable {
		// BE
		int ranNum = JUtil.getRandomNumber();
		String projectName = "P1HRM_N" + ranNum;
		CreateProject_POJO obj = new CreateProject_POJO("PARK", projectName, "Created", 0);
		Response res = given().spec(reqSpec).body(obj).when().post(IEndPoints.AddProj);
		res.then()
			.assertThat().statusCode(201)
			.assertThat().time(lessThan(5000L))
			.assertThat().body("msg", equalTo("Successfully Added"))
			.spec(resSpec)
			.log().all();
		String PROJ_NAME = res.jsonPath().get("projectName");
		// DB
		String PROJ_ID_F = DUtil.executeQueryAndGetData("select * from project where project_name='"+PROJ_NAME+"'", 1);

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
		WUtil.selectByVisibleText(driver.findElement(By.xpath("//select[@class='form-control']")), "Search by Project Name");
		driver.findElement(By.xpath("//input[@placeholder='Search by Project Name']")).sendKeys(PROJ_NAME);
		Thread.sleep(3000);
		String PROJ_ID=driver.findElement(By.xpath("//td[.='"+PROJ_NAME+"']/preceding-sibling::td")).getText();
		Assert.assertEquals(PROJ_ID_F, PROJ_ID);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[local-name()='svg' and @class='svg-inline--fa fa-right-from-bracket ']")).click();
		driver.quit();
	}
}
