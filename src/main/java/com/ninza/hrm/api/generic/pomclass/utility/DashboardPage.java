package com.ninza.hrm.api.generic.pomclass.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
	WebDriver driver;
	public DashboardPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[.='Projects']")
	private WebElement projectsLink;

	public WebElement getProjectsLink() {
		return projectsLink;
	}

	public void setProjectsLink(WebElement projectsLink) {
		this.projectsLink = projectsLink;
	}
}
