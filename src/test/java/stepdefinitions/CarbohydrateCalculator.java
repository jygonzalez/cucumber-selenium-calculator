package stepdefinitions;

import constants.FrameworkConstants;
import context.ScenarioContext;
import driver.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.CarbohydrateCalculatorPage;

public class CarbohydrateCalculator {

	private final ScenarioContext context;
	private CarbohydrateCalculatorPage carbohydrateCalculatorPage;

	public CarbohydrateCalculator(ScenarioContext context) {
		this.context = context;
	}

	@Given("User navigates to Home page")
	public void user_navigates_to_home_page() {
		DriverManager.getDriver().get(FrameworkConstants.URL);
	}

	@Given("User navigates to Carbohydrate Calculator page")
	public void user_navigates_to_carbohydrate_calculator_page() {
		DriverManager.getDriver().get(FrameworkConstants.URL + "/carbohydrate-calculator");
		carbohydrateCalculatorPage = new CarbohydrateCalculatorPage(DriverManager.getDriver());
	}

	@When("User enters age as {string}")
	public void user_enters_age_as(String age) {
		carbohydrateCalculatorPage.enterAge(age);
	}
}
