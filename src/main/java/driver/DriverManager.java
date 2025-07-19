package driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {

	// ThreadLocal ensures each thread gets its own independent WebDriver instance
	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	private DriverManager() {
		super();
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void setDriver(WebDriver webDriver) {
		DriverManager.driver.set(webDriver);
	}

	public static void quit() {
		WebDriver currentDriver = DriverManager.getDriver();
		if (currentDriver != null) {
			currentDriver.quit();
			driver.remove(); // TOOD: Verify if removing ThreadLocal value after quitting is necessary
		}
	}
}