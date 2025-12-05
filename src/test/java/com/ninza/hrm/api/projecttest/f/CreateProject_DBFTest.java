package com.ninza.hrm.api.projecttest.f;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.api.generic.baseclass.BaseAPIClass;
import com.ninza.hrm.api.generic.baseclass.BaseClass;
import com.ninza.hrm.api.generic.pomclass.utility.DashboardPage;
import com.ninza.hrm.api.generic.pomclass.utility.ProjectsPage;
import com.ninza.hrm.constants.endpoints.IEndPoints;

public class CreateProject_DBFTest extends BaseAPIClass {
	int ranNum = JUtil.getRandomNumber();
	String PROJ_NAME = "PHRM3_N_" + ranNum;
	String PROJ_ID = "PHRM3_I_" + ranNum;
	String CREATED_ON = JUtil.getSysDateInDDMMYYYY();
	static String PROJ_NAME_U;
	

	@Test
	public void createProjectDBTest() throws Throwable {
		int createdStatus = DUtil.executeUpdate("insert into project values('" + PROJ_ID + "','MARK','" + CREATED_ON
				+ "','" + PROJ_NAME + "','Created',0);");
		boolean availableStatus = DUtil.executeQueryVerifyAndGetData(
				"select * from project where project_id='" + PROJ_ID + "';", 4, PROJ_NAME);
		Assert.assertTrue(availableStatus);
	}
	 class UpdateProjectDBTest extends BaseAPIClass {
		
		@Test
		public void updateProjectDBTest() throws Throwable {
			PROJ_NAME_U = "PROJ_SC_N2_"+ JUtil.getRandomNumber();
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("createdBy", "marco");
			jsonobj.put("status", "Created");
			jsonobj.put("teamSize", 0);
			jsonobj.put("projectName", PROJ_NAME_U);
			given().spec(reqSpec).pathParam("projectId", PROJ_ID).body(jsonobj.toJSONString()).when()
					.put(IEndPoints.UpdateProj).then().spec(resSpec).assertThat().statusCode(200).log().all();
				}
	}
	 class DeleteProjectFETest extends BaseClass {
		@Test
		public void deleteProjectFETest() throws Throwable {
			DashboardPage DPage=new DashboardPage(driver);
			DPage.getProjectsLink().click();
			ProjectsPage PPage=new ProjectsPage(driver);
			PPage.searchProject(WUtil, CreateProject_DBFTest.PROJ_NAME_U);
			PPage.deleteProject(WUtil, CreateProject_DBFTest.PROJ_NAME_U);
			String ACT_DEL_MSG=PPage.getdeletionMsg();
			String EXP_DEL_MSG=CreateProject_DBFTest.PROJ_NAME_U+" Project Successfully Deleted";
			System.out.println(ACT_DEL_MSG);
			System.out.println(EXP_DEL_MSG);
			Assert.assertEquals(ACT_DEL_MSG, EXP_DEL_MSG,"Project deletion not verified in GUI");
//			boolean projectAvailabilityStatus = false;
//			try {
//				driver.navigate().refresh();
//				Thread.sleep(2000);
//				PPage.searchProject(WUtil, CreateProject_DBFTest.PROJ_NAME_U);
//				projectAvailabilityStatus = driver.findElement(By.xpath("//td[.='"+CreateProject_DBFTest.PROJ_NAME_U+"']")).isDisplayed();
//				System.out.println(projectAvailabilityStatus);
//				}catch(Exception e) {}
//			Assert.assertTrue(!projectAvailabilityStatus,"Project deletion not verified in GUI");
		}
	}
}
