package com.ninza.hrm.api.employeetest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.api.generic.baseclass.BaseAPIClass;
import com.ninza.hrm.api.generic.pojoclass.utility.CreateEmployee_POJO;
import com.ninza.hrm.api.generic.pojoclass.utility.CreateProject_POJO;
import com.ninza.hrm.constants.endpoints.IEndPoints;

import io.restassured.response.Response;

public class EmployeeTest extends BaseAPIClass{
	int ranNum=JUtil.getRandomNumber();
	@Test
	public void addEmployeeTest() throws Throwable {
		Thread.sleep(2000);
		// API-1: create project
		String projectName="HMR11" + ranNum;
		String userName="ASHLEYN" + ranNum;
		CreateProject_POJO obj = new CreateProject_POJO("PARK", projectName, "Created", 0);
		given()
			.spec(reqSpec)
			.body(obj)
		.when().post(IEndPoints.AddProj)
		.then()
			.spec(resSpec)
			.log().all();
		// API-2: create employee
		CreateEmployee_POJO ceObj = new CreateEmployee_POJO("Automation Engineer", "09/10/2025", "ashleyn@gmail.com",
				userName, 5, "7746589758", projectName, "ROLE_EMPLOYEE", userName);
		Response res = given()
							.spec(reqSpec)
							.body(ceObj)
						.when()
							.post(IEndPoints.AddEmp);
		res.then()
					.assertThat().statusCode(201)
					.assertThat().time(lessThan(5000L))
					.assertThat().body("msg", equalTo("Employee Added Successfully"))
					.spec(resSpec)
					.log().all();
		// Verify project name in DB
		boolean flag = DUtil.executeQueryVerifyAndGetData("select * from employee", 5, userName);
		Assert.assertTrue(flag, "Employee  in DB is not verified");
	}
	@Test
	
	public void addEmployeeWithoutEmailTest() throws Throwable {
		Thread.sleep(2000);
		// API-1: create project
		String projectName="HTR22" + ranNum;
		CreateProject_POJO obj = new CreateProject_POJO("PARK", projectName, "Created", 0);
		given()
				.spec(reqSpec).body(obj)
		.when().post(IEndPoints.AddProj)
		.then().log().all();
		// API-2: create employee
		String userName="sakshma" + ranNum;
		CreateEmployee_POJO ceObj = new CreateEmployee_POJO("Automation Engineer", "09/10/2025", "",
				userName, 5, "7746589758", projectName, "ROLE_EMPLOYEE", userName);
		Response res = given()
						.spec(reqSpec)
						.body(ceObj)
					.when().post(IEndPoints.AddEmp);
		res.then()
					.assertThat().statusCode(500)
					.spec(resSpec)
					.log().all();
	}

}
