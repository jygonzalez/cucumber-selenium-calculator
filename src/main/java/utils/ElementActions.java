package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import constants.FrameworkConstants;
import driver.DriverManager;

public class ElementActions {

	private final WebDriver driver;
	private final WebDriverWait wait;
	private final Actions actions;

	public ElementActions() {
		this.driver = DriverManager.getDriver();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstants.WAIT_EXPLICIT_TIMEOUT));
		this.actions = new Actions(driver);
	}

	public void click(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public void click(By by) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
		element.click();
	}

	public void moveAndClick(WebElement element) {
		actions.moveToElement(element).perform();
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public void moveAndClick(By by) {
		WebElement element = driver.findElement(by);
		actions.moveToElement(element).perform();
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public void type(WebElement element, String text) {
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
		element.sendKeys(text);
	}

	public String getText(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}

	public boolean isVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.isDisplayed();
	}

	public void moveTo(WebElement element) {
		actions.moveToElement(element).perform();
	}

	public void moveTo(By by) {
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
		actions.moveToElement(element).perform();
	}

	public void contextClick(WebElement element) {
		actions.contextClick(element).perform();
	}
}