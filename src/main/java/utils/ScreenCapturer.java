package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import driver.DriverManager;
import io.cucumber.java.Scenario;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

public class ScreenCapturer {

	@Attachment(value = "{0}", type = "image/png")
	public static byte[] attachScreenshotToAllure(String name) {
		return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
	}

	@Step("Capture screenshot on step failure")
	public static void screenshotOnFailure(Scenario scenario) {
		String testName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
		attachScreenshotToAllure(testName + "_Failure");
	}
}
