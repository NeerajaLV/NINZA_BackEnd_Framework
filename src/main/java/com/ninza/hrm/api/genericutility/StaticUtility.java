package com.ninza.hrm.api.genericutility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
/**
 * This class consists of static variables to use across multiple threads
 * @author neera
 */
public class StaticUtility {
	public static ThreadLocal<WebDriver> driver= new ThreadLocal<WebDriver>();
	public static ThreadLocal<ExtentTest> test= new ThreadLocal<ExtentTest>();
	public static WebDriver getDriver()
	{
		return driver.get();
	}
	public static void setDriver(WebDriver actDriver)
	{
		driver.set(actDriver);
	}
	public static ExtentTest getTest()
	{
		return test.get();
	}
	public static void setTest(ExtentTest actTest)
	{
		test.set(actTest);
	}
}
