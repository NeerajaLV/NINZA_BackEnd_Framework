package com.ninza.hrm.api.projecttest.f;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.api.generic.baseclass.BaseAPIClass;
import com.ninza.hrm.api.generic.baseclass.BaseClass;
import com.ninza.hrm.api.generic.pojoclass.utility.CreateProject_POJO;
import com.ninza.hrm.api.generic.pomclass.utility.DashboardPage;
import com.ninza.hrm.api.generic.pomclass.utility.ProjectsPage;
import com.ninza.hrm.constants.endpoints.IEndPoints;

import io.restassured.response.Response;

public class CreateProject_BDFTest extends BaseAPIClass {
	int ranNum = JUtil.getRandomNumber();
	String PROJ_NAME = "PHRM2_N_" + ranNum;
	static String PROJ_ID;
	static String PROJ_NAME_F;
	static String PROJ_ID_F;
	

	@Test
	public void createProjectBETest() throws Throwable {
		CreateProject_POJO obj = new CreateProject_POJO("PARK", PROJ_NAME, "Created", 0);
		Response res = given().spec(reqSpec).body(obj).when().post(IEndPoints.AddProj);
		res.then()
			.assertThat().statusCode(201)
			.assertThat().time(lessThan(5000L))
			.assertThat().body("msg", equalTo("Successfully Added"))
			.spec(resSpec)
			.log().all();
		PROJ_ID = res.jsonPath().get("projectId");
		System.out.println(PROJ_ID);
	}
	 class GetProjectDBTest extends BaseAPIClass {
		
		@Test
		public void getProjectDBTest() throws Throwable {
			PROJ_NAME_F = DUtil.executeQueryAndGetData("select * from project where project_id='"+CreateProject_BDFTest.PROJ_ID+"';", 4);
			System.out.println(PROJ_NAME_F);
		}
	}
	 class ValidateProjectFETest extends BaseClass {
		@Test
		public void validateProjectFETest() throws Throwable {
			DashboardPage DPage=new DashboardPage(driver);
			DPage.getProjectsLink().click();
			ProjectsPage PPage=new ProjectsPage(driver);
			PPage.searchProject(WUtil, CreateProject_BDFTest.PROJ_NAME_F);
			PROJ_ID_F=PPage.getProjectId(driver, CreateProject_BDFTest.PROJ_NAME_F);
			Assert.assertEquals(PROJ_ID_F, PROJ_ID);
		}
	}
}
