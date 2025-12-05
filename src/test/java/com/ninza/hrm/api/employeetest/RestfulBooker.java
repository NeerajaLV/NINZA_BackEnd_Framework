package com.ninza.hrm.api.employeetest;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestfulBooker {
	@Test
	public void createBooking()
	{
		String s= "{\r\n"
				+ "		    \"firstname\" : \"Jim\",\r\n"
				+ "		    \"lastname\" : \"Brown\",\r\n"
				+ "		    \"totalprice\" : 111,\r\n"
				+ "		    \"depositpaid\" : true,\r\n"
				+ "		    \"bookingdates\" : {\r\n"
				+ "		        \"checkin\" : \"2018-01-01\",\r\n"
				+ "		        \"checkout\" : \"2019-01-01\"\r\n"
				+ "		    },\r\n"
				+ "		    \"additionalneeds\" : \"Breakfast\"}";
		Response resp=given()
				.contentType(ContentType.JSON)
				.body(s)
			.when().post("https://restful-booker.herokuapp.com/booking");
		resp.then().statusCode(200).log().all();
		String s1= "{\r\n"
				+ "    \"firstname\" : \"James\",\r\n"
				+ "    \"lastname\" : \"Brown\"\r\n"
				+ "}";
		Response resp1=given()
				.header("Cookie","token=abc123")
				.header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
				.body(s1)	
				.pathParam("id", resp.jsonPath().get("bookingid"))
			.when().patch("https://restful-booker.herokuapp.com/booking/{id}");
		resp1.then().statusCode(200).log().all();
		
			}
}

