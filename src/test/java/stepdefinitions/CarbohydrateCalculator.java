package stepdefinitions;

import java.util.Map;

import org.testng.Assert;

import constants.FrameworkConstants;
import context.ScenarioContext;
import driver.DriverManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CarbohydrateCalculatorPage;

public class CarbohydrateCalculator {

	private final ScenarioContext context; // Context is not used so far, but it's used to persist values generated
											// during scenario runtime across steps
	private CarbohydrateCalculatorPage carbohydrateCalculatorPage;

	public CarbohydrateCalculator(ScenarioContext context) {
		this.context = context;
	}

	@Given("I navigate to Home page")
	public void i_navigate_to_home_page() {
		DriverManager.getDriver().get(FrameworkConstants.URL);
	}

	@Given("I navigate to Carbohydrate Calculator page")
	public void i_navigate_to_carbohydrate_calculator_page() {
		DriverManager.getDriver().get(FrameworkConstants.URL + "/carbohydrate-calculator");
		carbohydrateCalculatorPage = new CarbohydrateCalculatorPage(DriverManager.getDriver());
	}

	@When("I select US Units")
	public void i_select_us_units() {
		carbohydrateCalculatorPage.clickUSUnits();
	}

	@When("I select Metric Units")
	public void i_select_metric_units() {
		carbohydrateCalculatorPage.clickMetricUnits();
	}

	@When("I enter the following details in the input table:")
	public void i_enter_the_following_details_in_the_input_table(DataTable dataTable) {
		Map<String, String> data = dataTable.asMap(String.class, String.class);

		if (data.containsKey("Age")) {
			carbohydrateCalculatorPage.enterAge(data.get("Age"));
		}
		if (data.containsKey("Gender")) {
			carbohydrateCalculatorPage.selectGender(data.get("Gender"));
		}
		if (data.containsKey("Height (feet)") && data.containsKey("Height (inches)")) {
			carbohydrateCalculatorPage.enterHeightUS(data.get("Height (feet)"), data.get("Height (inches)"));
		} else if (data.containsKey("Height (cm)")) {
			carbohydrateCalculatorPage.enterHeightMetric(data.get("Height (cm)"));
		}
		if (data.containsKey("Weight (lbs)")) {
			carbohydrateCalculatorPage.enterWeightUS(data.get("Weight (lbs)"));
		} else if (data.containsKey("Weight (kg)")) {
			carbohydrateCalculatorPage.enterWeightMetric(data.get("Weight (kg)"));
		}
		if (data.containsKey("Activity")) {
			carbohydrateCalculatorPage.selectActivity(data.get("Activity"));
		}
	}

	@When("I select Mifflin St Jeor as the BMR estimation formula")
	public void i_select_mifflin_st_jeor_as_the_bmr_estimation_formula() {
		carbohydrateCalculatorPage.selectMifflinStJeorAsBMR();
	}

	@When("I enter percent body fat as {string}")
	public void i_enter_percent_body_fat_as(String bodyFat) {
		carbohydrateCalculatorPage.enterBodyFatPercentage(bodyFat);
	}

	@When("I enter value for Age as {string}")
	public void i_enter_value_for_age_as(String age) {
		carbohydrateCalculatorPage.enterAge(age);
	}

	@When("I click away on the screen")
	public void i_click_away_on_the_screen() {
		carbohydrateCalculatorPage.clickAway();
	}

	@Then("An error message appears for Age with text {string}")
	public void an_error_message_appears_for_age_field(String expectedErrorMessage) {
		Assert.assertTrue(carbohydrateCalculatorPage.isAgeErrorMessageDisplayed(expectedErrorMessage),
				"Error message '" + expectedErrorMessage + "' for age field was not displayed.");
	}

	@Then("An error message appears on screen with text {string}")
	public void an_error_message_appears_on_screen_with_text(String expectedErrorMessage) {
		String actualMessage = carbohydrateCalculatorPage.getAgeErrorMessageText();
		Assert.assertTrue(actualMessage.contains(expectedErrorMessage),
				"Expected error message to contain: '" + expectedErrorMessage + "' but was: '" + actualMessage + "'");
	}

	@Then("No error messages should be displayed")
	public void no_error_messages_should_be_displayed() {
		Assert.assertTrue(carbohydrateCalculatorPage.isErrorMessageHidden(),
				"Age error message is still displayed when it should not be.");
	}

	@When("I click on the Calculate button")
	public void i_click_on_the_calculate_button() {
		carbohydrateCalculatorPage.clickCalculateButton();
	}

	@When("I select Katch-McArdle as the BMR estimation formula")
	public void i_select_katch_mc_ardle_as_the_bmr_estimation_formula() {
		carbohydrateCalculatorPage.selectKatchMcArdleAsBMR();
	}

	@Then("The results table should contain carb gram value {int}")
	public void the_results_table_should_contain_gram_value(Integer expectedGramValue) {
		String expectedGram = expectedGramValue + " grams";

		Map<String, Map<String, String>> actualTable = carbohydrateCalculatorPage.getResultsTable();

		boolean found = actualTable.values().stream().flatMap(row -> row.values().stream())
				.anyMatch(value -> value.contains(expectedGram));

		Assert.assertTrue(found, "Expected gram value \"" + expectedGram + "\" was not found in the results table.");
	}

}
