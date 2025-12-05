package com.ninza.hrm.api.genericutility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class consists of generic methods to use selenium webdriver utilities
 * @author neera
 */
public class WebDriverUtility {
	/**
	 * This method will maximize window 
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	/**
	 * This method will navigate to the URL
	 * @param driver
	 * @param url
	 */
	public void navigateTo(WebDriver driver, String url) {
			driver.navigate().to(url);
	}
	/**
	 * This method will refresh the current window
	 * @param driver
	 */
	public void navigateRefresh(WebDriver driver) {
			driver.navigate().refresh();
	}
	/**
	 * This method will navigate to backward window
	 * @param driver
	 */
	public void navigateBack(WebDriver driver) {
			driver.navigate().back();
	}
	/**
	 * This method will navigate to forward window
	 * @param driver
	 */
	public void navigateForward(WebDriver driver) {
			driver.navigate().forward();
	}
	/**
	 * This method will implicitly waits till page is loaded
	 * @param driver
	 */
	public void waitForPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	/**
	 * This method will explicitly waits till element is present
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBePresent(WebDriver driver, WebElement element)
	{
		WebDriverWait ew=new WebDriverWait(driver,Duration.ofSeconds(20));
		ew.until(ExpectedConditions.visibilityOf(element));
	}
	/**
	 * This method will explicitly waits till element is clickable
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClickable(WebDriver driver, WebElement element)
	{
		WebDriverWait ew=new WebDriverWait(driver,Duration.ofSeconds(40));
		ew.until(ExpectedConditions.elementToBeClickable(element));
	}
	/**
	 * This method will explicitly waits till element is to be selected
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeSelected(WebDriver driver, WebElement element)
	{
		WebDriverWait ew=new WebDriverWait(driver,Duration.ofSeconds(20));
		ew.until(ExpectedConditions.elementToBeSelected(element));
	}
	/**
	 * This method will explicitly waits till element is selected
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeSelectedTrue(WebDriver driver, WebElement element)
	{
		WebDriverWait ew=new WebDriverWait(driver,Duration.ofSeconds(20));
		ew.until(ExpectedConditions.elementSelectionStateToBe(element,true));
	}

	/**
	 * This method is used to switch to tab/window using iterator with partial URL
	 * @param driver
	 * @param partialUrl
	 */
	public void switchToTabOnUrlUsingIterator(WebDriver driver, String partialUrl)
	{
		Set<String> wins=driver.getWindowHandles();
		Iterator<String> i = wins.iterator();
		while(i.hasNext())
		{
			String win=i.next();
			driver.switchTo().window(win);
			if(driver.getCurrentUrl().contains(partialUrl))
			{
				
			}
		}
	}
	/**
	 * This method is used to switch to tab/window using iterator with partial Title
	 * @param driver
	 * @param partialTitle
	 */
	public void switchToTabOnTitleUsingIterator(WebDriver driver, String partialTitle)
	{
		Set<String> wins=driver.getWindowHandles();
		Iterator<String> i = wins.iterator();
		while(i.hasNext())
		{
			String win=i.next();
			driver.switchTo().window(win);
			if(driver.getCurrentUrl().contains(partialTitle))
			{
				
			}
		}
	}
	/**
	 * This method is used to switch to parent tab/window
	 * @param driver
	 */
	public void switchToParentTab(WebDriver driver)
	{
		String win=driver.getWindowHandle();
		driver.switchTo().window(win);
	}
	/**
	 * This method is used to switch to tab/window using for-each loop with partial URL
	 * @param driver
	 * @param partialUrl
	 */
	public void switchToTabOnUrl(WebDriver driver, String partialUrl)
	{
		Set<String> wins=driver.getWindowHandles();
		for(String win: wins)
		{
			driver.switchTo().window(win);
			if(driver.getCurrentUrl().contains(partialUrl))
			{
				
			}
		}
	}
	public void switchToTabCB(WebDriver driver, String partialUrl)
	{
		Set<String> wins=driver.getWindowHandles();
		for(String win: wins)
		{
			driver.switchTo().window(win);
		}
	}
	/**
	 * This method is used to switch to tab/window using for-each loop with partial Title
	 * @param driver
	 * @param partialTitle
	 */
	public void switchToTabOnTitle(WebDriver driver, String partialTitle)
	{
		Set<String> wins=driver.getWindowHandles();
		for(String win: wins)
		{
			driver.switchTo().window(win);
			if(driver.getCurrentUrl().contains(partialTitle))
			{
				
			}
		}
	}
	
	/**
	 * This method will handle frame by frame name or id
	 * @param driver
	 * @param nameId
	 */
	public void switchToFrame(WebDriver driver, String nameId)
	{
		driver.switchTo().frame(nameId);
	}
	/**
	 * This method will handle frame by frame index
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	/**
	 * This method will handle frame by web element
	 * @param driver
	 * @param element
	 */
	public void switchToFrame(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);
	}
	/**
	 * This method will handle frame by switching to parent frame
	 * @param driver
	 * @param index
	 */
	public void switchToParentFrame(WebDriver driver) {
		driver.switchTo().parentFrame();
	}
	/**
	 * This method will handle frame by switching to main/first frame
	 * @param driver
	 * @param index
	 */
	public void switchToMainFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	/**
	 * This method will handle alert by clicking on OK
	 * @param driver
	 */
	public void switchToAlertAndAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	/**
	 * This method will handle alert by clicking on cancel
	 * @param driver
	 */
	public void switchToAlertAndDismiss(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	/**
	 * This method will handle alert by capturing alert text and return it to caller
	 * @param driver
	 * @return
	 */
	public String switchToAlertAndGetText(WebDriver driver)
	{
		String txt=driver.switchTo().alert().getText();
		return txt;
	}
	/**
	 * This method will handle alert by sending key values
	 * @param driver
	 * @param data
	 */
	public void switchToAlertAndSetText(WebDriver driver, String data)
	{
		driver.switchTo().alert().sendKeys(data);
	}
	/**
	 * This method will return x co-ordinate of element
	 * @param element
	 * @return
	 */
	public int getElementLocationX(WebElement element)
	{
		Point p = element.getLocation();
		int x=p.getX();
		return x;
	}
	/**
	 * This method will return y co-ordinate of element
	 * @param element
	 * @return
	 */
	public int getElementLocationY(WebElement element)
	{
		Point p = element.getLocation();
		int y=p.getY();
		return y;
	}
	//Actions
	/**
	 * This method will handle mouse hover action of moving to center of element
	 * @param driver
	 * @param element
	 */
	public void mouseMoveOnElement(WebDriver driver, WebElement element)
	{
		Actions a=new Actions(driver);
		a.moveToElement(element).perform();
	}
	/**
	 * This method will handle mouse hover action of moving to center of element and click
	 * @param driver
	 * @param element
	 */
	public void mouseMoveOnElementAndClick(WebDriver driver, WebElement element)
	{
		Actions a=new Actions(driver);
		a.moveToElement(element).click().build().perform();
	}
	/**
	 * This method will handle mouse hover action of clicking on element
	 * @param driver
	 * @param element
	 */
	public void clickOnElement(WebDriver driver, WebElement element)
	{
		Actions a=new Actions(driver);
		a.click(element).perform();
	}
	/**
	 * This method will handle mouse hover action of double clicking on element
	 * @param driver
	 * @param element
	 */
	public void doubleClickOnElement(WebDriver driver, WebElement element)
	{
		Actions a=new Actions(driver);
		a.doubleClick(element).perform();
	}
	/**
	 * This method will handle mouse hover action of right click of element
	 * @param driver
	 * @param element
	 */
	public void contextClickOnElement(WebDriver driver, WebElement element)
	{
		Actions a=new Actions(driver);
		a.contextClick(element).perform();
	}
	/**
	 * This method will handle mouse hover action of drag & drop of element
	 * @param driver
	 * @param src
	 * @param target
	 */
	public void dragAndDropElement(WebDriver driver, WebElement src, WebElement target)
	{
		Actions a=new Actions(driver);
		a.dragAndDrop(src, target).perform();
	}
	/**
	 * This method will handle mouse hover action of drag & drop of element to given offset
	 * @param driver
	 * @param src
	 * @param x
	 * @param y
	 */
	public void dragAndDropElementByOffset(WebDriver driver, WebElement src, int x, int y)
	{
		Actions a=new Actions(driver);
		a.dragAndDropBy(src, x, y).perform();
	}
	/**
	 * This method will handle mouse hover action of click & hold and release of element
	 * @param driver
	 * @param element1
	 * @param element2
	 */
	public void clickAndHoldElement(WebDriver driver, WebElement element)
	{
		Actions a=new Actions(driver);
		a.clickAndHold(element).release(element).build().perform();
	}
	/**
	 * This method will handle mouse hover action of click & hold and release based on given offset of element
	 * @param driver
	 * @param element
	 * @param x
	 * @param y
	 */
	public void clickAndHoldElementByOffset(WebDriver driver, WebElement element, int x, int y)
	{
		Actions actions=new Actions(driver);
		actions.moveToElement(element).clickAndHold(element).moveByOffset(x,y).release().build().perform();
	}
	/**
	 * This method will handle scroll bar to move to element
	 * @param driver
	 * @param element
	 */
	public void scrollToElement(WebDriver driver, WebElement element)
	{
		Actions a=new Actions(driver);
		a.scrollToElement(element).perform();
	}
	
	//Handling dropdown
	/**
	 * This method will handle drop down by using index  of the option
	 * @param element
	 * @param index
	 */
	public void selectByIndex(WebElement element, int index)
	{
		Select s=new Select(element);
		s.selectByIndex(index);
	}
	/**
	 * This method will handle drop down by using value of the option
	 * @param element
	 * @param value
	 */
	public void selectByValue(WebElement element, String value)
	{
		Select s=new Select(element);
		s.selectByValue(value);
	}
	/**
	 * This method will handle drop down by using visible text of the option
	 * @param element
	 * @param text
	 */
	public void selectByVisibleText(WebElement element, String text)
	{
		Select s=new Select(element);
		s.selectByVisibleText(text);
	}
	 /**
	  * This method will captures screenshot of current window and returns path of the screenshot
	  * @param driver
	  * @param screeshotName
	  * @return
	  * @throws IOException
	  */
	public String capturePageScreenshot(WebDriver driver, String screeshotName) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File temp=ts.getScreenshotAs(OutputType.FILE);
		File perm=new File("./Screenshots/"+screeshotName+".png");
		FileHandler.copy(temp, perm);
		return perm.getAbsolutePath();
	}
	/**
	 * This method will captures screenshot of element and returns path of the screenshot
	 * @param driver
	 * @param element
	 * @param screeshotName
	 * @throws IOException
	 */
	public String captureElementScreenshot(WebDriver driver, WebElement element, String screeshotName) throws IOException
	{
		File temp=element.getScreenshotAs(OutputType.FILE);
		File perm=new File("./Screenshots/"+screeshotName+".png");
		FileHandler.copy(temp, perm);
		return perm.getAbsolutePath();
	}
	//JavascriptExecutor
	/**
	 * This method will handle disabled element to enter data using JSElement
	 * @param driver
	 * @param JSElement
	 * @param data
	 */
	public void handleDisabledElementAndEnterValue_UingJSE(WebDriver driver, WebElement JSElement, String data)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript(JSElement+".value='"+data+"'");
	}
	/**
	 * This method will handle scroll bar to move to element using JSElement
	 * @param driver
	 * @param JSElement
	 * @param element
	 */
	public void scrollToElement_UingJSE(WebDriver driver, WebElement JSElement, WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript(JSElement+".scrollIntoView()", element);
	}
	/**
	 * This method will handle disabled element to enter data using Web Element
	 * @param driver
	 * @param element
	 * @param data
	 */
	public void handleDisabledElementAndEnterValue(WebDriver driver, WebElement element, String data)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].value='"+data+"'", element);
	}
	/**
	 * This method will handle scroll bar to move to element using Web Element
	 * @param driver
	 * @param element
	 */
	public void scrollToElementUsingJSE(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
	}
	/**
	 * This method will handle click on element using Web Element
	 * @param driver
	 * @param element
	 */
	public void clickElementUsingJSE(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", element);
	}
	/**
	 * This method will handle scroll bar to element by x and y co-ordinates
	 * @param driver
	 * @param x
	 * @param y
	 */
	public void scrollToPoint(WebDriver driver, int x, int y)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy("+x+","+y+")");
	}
	/**
	 * This method will handle scroll bar to window top
	 * @param driver
	 */
	public void scrollToPageTop(WebDriver driver)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
	}
	/**
	 * This method will handle scroll bar to window bottom
	 * @param driver
	 * @param element
	 */
	public void scrollToPageBottom(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	/**
	 * This method will handle window zoom in and out
	 * @param driver
	 * @param percentage
	 */
	public void zoomInAndOutPage(WebDriver driver, int percentage)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("document.body.style.zoom='"+percentage+"%'");
	}
}
