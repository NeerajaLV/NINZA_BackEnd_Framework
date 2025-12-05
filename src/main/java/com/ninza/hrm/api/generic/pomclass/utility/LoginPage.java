package com.ninza.hrm.api.generic.pomclass.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="username")
	private WebElement usernameTxtField;
	@FindBy(id="inputPassword")
	private WebElement passwordTxtField;
	@FindBy(xpath="//button[.='Sign in']")
	private WebElement submitBtn;
	
	public void loginToApp(WebDriver driver, String USERNAME, String PASSWORD) throws Throwable
	{
		driver.get("http://49.249.28.218:8091/");
		driver.manage().window().maximize();

		usernameTxtField.sendKeys(USERNAME);
		passwordTxtField.sendKeys(PASSWORD);
		submitBtn.click();	
		Thread.sleep(3000);
	}
	
	public WebElement getUsernameTxtField() {
		return usernameTxtField;
	}
	public void setUsernameTxtField(WebElement usernameTxtField) {
		this.usernameTxtField = usernameTxtField;
	}
	public WebElement getPasswordTxtField() {
		return passwordTxtField;
	}
	public void setPasswordTxtField(WebElement passwordTxtField) {
		this.passwordTxtField = passwordTxtField;
	}
	public WebElement getSubmitBtn() {
		return submitBtn;
	}
	public void setSubmitBtn(WebElement submitBtn) {
		this.submitBtn = submitBtn;
	}
}
