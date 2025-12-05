package com.ninza.hrm.api.genericutility;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

/**
 * This class contains generic methods to get data using JsonPath
 * 
 * @author neera
 */
public class JsonPathUtility {
	PropertyFileUtility pfUtil=new PropertyFileUtility();
	/**
	 * Get the Json data based on json complex path
	 * 
	 * @param resp
	 * @param jsonXpath
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public String getDataOnJsonPath(Response resp, String jsonXpath) throws Throwable {
		List<String> list = JsonPath.read(resp.asString(), jsonXpath);
		return list.get(0).toString();
	}

	/**
	 * Get the xml data based on xml complex path
	 * 
	 * @param resp
	 * @param xmlpath
	 * @return
	 */
	public String getDataOnXpath(Response resp, String xmlpath) {
		return resp.xmlPath().get(xmlpath);
	}

	/**
	 * Verify the data in json bosy jusing jsonxpath
	 * 
	 * @param resp
	 * @param jsonXpath
	 * @param expectedData
	 * @return
	 */
	public boolean verifyDataOnJsonPath(Response resp, String jsonXpath, String expectedData) {
		List<String> list = JsonPath.read(resp.asString(), jsonXpath);
		boolean flag = false;
		for (String str : list) {
			if (str.equals(expectedData)) {
				flag = true;
			}
			if (flag)
				return flag;
		}
		return flag;
	}
	/**
	 * Returns bearer token
	 * @return
	 * @throws Throwable 
	 */
	public String getAccessToken() throws Throwable {
		Response res = given().formParam("client_id", pfUtil.getDataFromPropertiesFile("ClientID"))
				.formParam("client_secret", pfUtil.getDataFromPropertiesFile("ClientSecret"))
				.formParam("grant_type", "client_credentials").when()
				.post("http://49.249.28.218:8180/auth/realms/ninza/protocol/openid-connect/token");
		res.then().log().all();
		String token = res.jsonPath().get("access_token");
		return token;
	}
}
