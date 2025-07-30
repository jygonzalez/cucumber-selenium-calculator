package hooks;

import org.openqa.selenium.WebDriver;

import constants.FrameworkConstants;
import driver.DriverFactory;
import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ScreenCapturer;

public class Hooks {

	@Before
	public void beforeScenario() {
		WebDriver driver = DriverFactory.createDriver(FrameworkConstants.BROWSER);
		DriverManager.setDriver(driver);
	}

	@AfterStep
	public void afterStep(Scenario scenario) {
		if (scenario.isFailed()) {
			ScreenCapturer.screenshotOnFailure(scenario);
		}
	}

	@After
	public void afterScenario() {
		DriverManager.quit();
	}
}
