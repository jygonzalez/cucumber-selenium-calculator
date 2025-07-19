package hooks;

import org.openqa.selenium.WebDriver;

import constants.FrameworkConstants;
import driver.DriverFactory;
import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

	@Before
	public void beforeScenario() {
		WebDriver driver = DriverFactory.createDriver(FrameworkConstants.BROWSER);
		DriverManager.setDriver(driver);
	}

	@After
	public void afterScenario() {
		DriverManager.quit();
	}
}
