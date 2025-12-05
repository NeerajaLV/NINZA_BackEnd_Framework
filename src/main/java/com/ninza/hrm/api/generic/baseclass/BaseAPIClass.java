package com.ninza.hrm.api.generic.baseclass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.ninza.hrm.api.genericutility.DatabaseUtility;
import com.ninza.hrm.api.genericutility.ExcelFileUtility;
import com.ninza.hrm.api.genericutility.JavaFileUtility;
import com.ninza.hrm.api.genericutility.PropertyFileUtility;
import com.ninza.hrm.api.genericutility.WebDriverUtility;

public class BaseAPIClass {
	// create object
	public PropertyFileUtility PUtil = new PropertyFileUtility();
	public WebDriverUtility WUtil = new WebDriverUtility();
	public ExcelFileUtility EUtil = new ExcelFileUtility();
	public JavaFileUtility JUtil = new JavaFileUtility();
	public DatabaseUtility DUtil = new DatabaseUtility();

	public static RequestSpecification reqSpec;
	public static ResponseSpecification resSpec;

	@BeforeSuite(alwaysRun=true)
	public void configBS() throws Throwable {
		System.out.println("<======GetDBConection======>");
		DUtil.getConnection();
		String BaseUri=PUtil.getDataFromPropertiesFile("BASEUri");
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setContentType(ContentType.JSON);
		builder.setBaseUri(BaseUri);
		reqSpec = builder.build();

		ResponseSpecBuilder resbuilder = new ResponseSpecBuilder();
		resbuilder.expectContentType(ContentType.JSON);
		resSpec = resbuilder.build();
	}
	@AfterSuite(alwaysRun=true)
	public void configAS() throws Throwable {
		DUtil.closeConnection();
		System.out.println("<======CloseDBConection======>");
	}
}
