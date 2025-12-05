	package com.ninza.hrm.api.projecttest;
	
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.edge.EdgeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.testng.Assert;
	import org.testng.annotations.Parameters;
	import org.testng.annotations.Test;
	import static io.restassured.RestAssured.*;
	import static org.hamcrest.Matchers.*;
	
	import java.util.HashMap;
	import java.util.Map;
	
	import com.ninza.hrm.api.generic.baseclass.BaseAPIClass;
import com.ninza.hrm.api.generic.baseclass.BaseClass;
import com.ninza.hrm.api.generic.pomclass.utility.DashboardPage;
import com.ninza.hrm.api.generic.pomclass.utility.LoginPage;
import com.ninza.hrm.api.generic.pomclass.utility.ProjectsPage;
import com.ninza.hrm.constants.endpoints.IEndPoints;
	
	import io.restassured.response.Response;
	
	class CreateProject_FBDTest extends BaseClass {
		@Test
		public void createProjectTest() throws Throwable {
			//FE
			DashboardPage DPage=new DashboardPage(driver);
			DPage.getProjectsLink().click();
			ProjectsPage PPage=new ProjectsPage(driver);
			PPage.getCreateProjectBtn().click();
			int ranNum = JUtil.getRandomNumber();
			String PROJ_NAME = "PHRM_N_" + ranNum;
			PPage.createProject(WUtil, PROJ_NAME, "Max", "Created");
			PPage.searchProject(WUtil, PROJ_NAME);
			PPage.logout(driver);
			String PROJ_ID=PPage.getProjectId(driver, PROJ_NAME);
			// BE
//			Response resp = given().spec(reqSpec).pathParam("projectId", PROJ_ID).when().get(IEndPoints.GetProj);
//			resp.then().assertThat().statusCode(200).assertThat().body("projectName", equalTo(PROJ_NAME)).log().all();
//			String PROJ_NAME_F = resp.jsonPath().get("projectName");
//			// DB
//			boolean flag = DUtil.executeQueryVerifyAndGetData("select * from project", 4, PROJ_NAME_F);
//			Assert.assertTrue(flag, "Project Name in DB is not verified");
	
		}
	}

