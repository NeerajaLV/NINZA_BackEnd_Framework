package com.ninza.hrm.api.projecttest.f;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.api.generic.baseclass.BaseAPIClass;
import com.ninza.hrm.api.generic.baseclass.BaseClass;
import com.ninza.hrm.api.generic.pomclass.utility.DashboardPage;
import com.ninza.hrm.api.generic.pomclass.utility.ProjectsPage;
import com.ninza.hrm.constants.endpoints.IEndPoints;

import io.restassured.response.Response;

public class CreateProject_FBDTest extends BaseClass{
	int ranNum = JUtil.getRandomNumber();
	String PROJ_NAME = "PHRM1_N_" + ranNum;
	static String PROJ_ID;
	static String PROJ_NAME_F;
	

	@Test
	public void createProjectFETest() throws Throwable {
		DashboardPage DPage=new DashboardPage(driver);
		DPage.getProjectsLink().click();
		ProjectsPage PPage=new ProjectsPage(driver);
		PPage.getCreateProjectBtn().click();		
		PPage.createProject(WUtil, PROJ_NAME, "Max", "Created");
		PPage.searchProject(WUtil, PROJ_NAME);
		PROJ_ID=PPage.getProjectId(driver, PROJ_NAME);
	}
	 class GetProjectBETest extends BaseAPIClass {
		
		@Test
		public void getProjectBETest() {
			Response resp = given().spec(reqSpec).pathParam("projectId", CreateProject_FBDTest.PROJ_ID).when().get(IEndPoints.GetProj);
			resp.then().assertThat().statusCode(200).assertThat().body("projectName", equalTo(PROJ_NAME)).log().all();
			PROJ_NAME_F = resp.jsonPath().get("projectName");
			
		}
	}
	 class ValidateProjectDBTest extends BaseAPIClass {
		@Test
		public void validateProjectDBTest() throws Throwable {
			boolean flag = DUtil.executeQueryVerifyAndGetData("select * from project", 4, CreateProject_FBDTest.PROJ_NAME_F);
			Assert.assertTrue(flag, "Project Name in DB is not verified");
		}
	}
}
