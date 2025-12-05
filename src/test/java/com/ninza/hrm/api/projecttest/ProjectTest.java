package com.ninza.hrm.api.projecttest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.api.generic.baseclass.BaseAPIClass;
import com.ninza.hrm.api.generic.pojoclass.utility.CreateProject_POJO;
import com.ninza.hrm.constants.endpoints.IEndPoints;

import io.restassured.response.Response;

public class ProjectTest extends BaseAPIClass{
	CreateProject_POJO obj;
	int ranNum=JUtil.getRandomNumber();
	@Test
	public void addSingleProjectWithCreated() throws Throwable {
		Thread.sleep(2000);
		//create project
		String projectName="HTR11" + ranNum;
		obj = new CreateProject_POJO("PARK", projectName, "Created", 0);
		Response res = given()
						.spec(reqSpec)
						.body(obj)
					.when()
						.post(IEndPoints.AddProj);
		res.then()
				.assertThat().statusCode(201)
				.assertThat().time(lessThan(5000L))
				.assertThat().body("msg", equalTo("Successfully Added"))
				.spec(resSpec)
				.log().all();
		String ProjectName = res.jsonPath().get("projectName");
		Assert.assertEquals(ProjectName, projectName);
		// Verify project name in DB
		boolean flag = DUtil.executeQueryVerifyAndGetData("select * from project", 4, ProjectName);
		Assert.assertTrue(flag, "Project Name in DB is not verified");

	}

	@Test /* (dependsOnMethods = "addSingleProjectWithCreated") */
	public void createDuplicateProjectTest() throws Throwable {
		Thread.sleep(2000);
		given()
			.spec(reqSpec)
			.body(obj)
		.when()
			.post(IEndPoints.AddProj)
		.then()
			.assertThat().statusCode(409)
			.spec(resSpec)
			.log().all();
	}
}
