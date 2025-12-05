package practice;
import static io.restassured.RestAssured.*;

import java.util.Random;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Printing_Response {
@Test
public void printTest() {
	JSONObject obj=new JSONObject();
	obj.put("createdBy", "string");
	obj.put("projectName", "Sample"+new Random().nextInt(3000));
	obj.put("status", "string");
	obj.put("teamSize", "string");
	
	Response resp=given().contentType(ContentType.JSON).body(obj.toJSONString())
			.when().post("http://49.249.28.218:8091/addProject");
	
	resp.prettyPrint();
	System.out.println("====================Pretty Print=================");
	
	resp.prettyPeek();
	System.out.println("====================Pretty Peek=================");
	
	resp.asString();
	System.out.println("====================As String=================");
	System.out.println("=====================================");
	System.out.println("====================Pretty Print=================");
	System.out.println(resp.prettyPrint());
	System.out.println("====================Pretty Peek=================");
	System.out.println(resp.prettyPeek());
	System.out.println("====================As String=================");
	System.out.println(resp.asString());
}
}
