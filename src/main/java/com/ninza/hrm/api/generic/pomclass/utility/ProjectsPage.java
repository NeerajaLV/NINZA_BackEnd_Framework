package com.ninza.hrm.api.generic.pomclass.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ninza.hrm.api.genericutility.WebDriverUtility;

public class ProjectsPage {
	WebDriver driver;
	public ProjectsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[.='Create Project']")
	private WebElement createProjectBtn;
	@FindBy(name="projectName")
	private WebElement projectNameTxtField;
	@FindBy(name="createdBy")
	private WebElement createdByTxtField;
	@FindBy(xpath="//label[.='Project Status* ']/following-sibling::select[@name='status']")
	private WebElement projectStatusDD;
	@FindBy(xpath="//input[@value='Add Project']")
	private WebElement submitBtn;
	@FindBy(xpath="//select[@class='form-control']")
	private WebElement searchCategoryDD;
	@FindBy(xpath="//input[@placeholder='Search by Project Name']")
	private WebElement searchTxtField;
	@FindBy(xpath="//*[local-name()='svg' and @class='svg-inline--fa fa-right-from-bracket ']")
	private WebElement logoutIcon;
	@FindBy(xpath="//div[@class='Toastify__toast-body']")
	private WebElement deletionMsg;
	
	
	public void createProject(WebDriverUtility WUtil, String PROJ_NAME, String CREATED_BY, String PROJ_STATUS) throws Throwable
	{
		projectNameTxtField.sendKeys(PROJ_NAME);
		createdByTxtField.sendKeys(CREATED_BY);
		WUtil.selectByVisibleText(projectStatusDD, PROJ_STATUS);
		submitBtn.click();
		Thread.sleep(3000);
	}
	public void searchProject(WebDriverUtility WUtil, String PROJ_NAME) throws Throwable
	{
		WUtil.selectByVisibleText(searchCategoryDD, "Search by Project Name");
		Thread.sleep(2000);
		searchTxtField.sendKeys(PROJ_NAME);
		Thread.sleep(3000);
	}
	public String getProjectId(WebDriver driver, String PROJ_NAME) throws Throwable
	{
		Thread.sleep(2000);
		String projectId = driver.findElement(By.xpath("//td[.='" + PROJ_NAME + "']/preceding-sibling::td")).getText();
		return projectId;
	}
	
	public void deleteProject(WebDriverUtility WUtil, String PROJ_NAME_U) throws Throwable
	{
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[.='" + PROJ_NAME_U + "']/following-sibling::td[5]/a[@class='delete']")).click();
		Thread.sleep(4000);
		WUtil.mouseMoveOnElementAndClick(driver, driver.findElement(By.xpath("//input[@type='button' and @value='Delete']")));
		Thread.sleep(3000);
	}
	public String getdeletionMsg() throws Throwable
	{
		Thread.sleep(2000);
		String msg=deletionMsg.getText();
		return msg;

	}
	public void logout(WebDriver driver)
	{
		logoutIcon.click();
		System.out.println("===Close Browser===");
		driver.quit();
	}

	public WebElement getCreateProjectBtn() {
		return createProjectBtn;
	}

	public void setCreateProjectBtn(WebElement createProjectBtn) {
		this.createProjectBtn = createProjectBtn;
	}

	public WebElement getProjectNameTxtField() {
		return projectNameTxtField;
	}

	public void setProjectNameTxtField(WebElement projectNameTxtField) {
		this.projectNameTxtField = projectNameTxtField;
	}

	public WebElement getCreatedByTxtField() {
		return createdByTxtField;
	}

	public void setCreatedByTxtField(WebElement createdByTxtField) {
		this.createdByTxtField = createdByTxtField;
	}

	public WebElement getProjectStatusDD() {
		return projectStatusDD;
	}

	public void setProjectStatusDD(WebElement projectStatusDD) {
		this.projectStatusDD = projectStatusDD;
	}

	public WebElement getSubmitBtn() {
		return submitBtn;
	}

	public void setSubmitBtn(WebElement submitBtn) {
		this.submitBtn = submitBtn;
	}

	public WebElement getSearchCategoryDD() {
		return searchCategoryDD;
	}

	public void setSearchCategoryDD(WebElement searchCategoryDD) {
		this.searchCategoryDD = searchCategoryDD;
	}

	public WebElement getSearchTxtField() {
		return searchTxtField;
	}

	public void setSearchTxtField(WebElement searchTxtField) {
		this.searchTxtField = searchTxtField;
	}

	public WebElement getLogoutIcon() {
		return logoutIcon;
	}

	public void setLogoutIcon(WebElement logoutIcon) {
		this.logoutIcon = logoutIcon;
	}
	
}
