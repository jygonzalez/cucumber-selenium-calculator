package pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ElementActions;

public class CarbohydrateCalculatorPage {

	private final WebDriver driver;
	private final ElementActions actions;

	public CarbohydrateCalculatorPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.actions = new ElementActions();
	}

	// *************************** LOCATORS *************************** //

	// UNITS (TOP MENU)
	@FindBy(xpath = "//a[text()='US Units']")
	private WebElement usUnitsLink;

	@FindBy(xpath = "//a[text()='Metric Units']")
	private WebElement metricUnitsLink;

	@FindBy(xpath = "//a[text()='Other Units']")
	private WebElement otherUnitsLink;

	// AGE
	@FindBy(id = "cage")
	private WebElement ageInput;

	// GENDER
	@FindBy(xpath = "//label[@for='csex1']")
	private WebElement maleGenderRadioBtn;

	@FindBy(xpath = "//label[@for='csex2']")
	private WebElement femaleGenderRadioBtn;

	// HEIGHT
	@FindBy(id = "cheightfeet")
	private WebElement heightFeetInput;

	@FindBy(id = "cheightinch")
	private WebElement heightInchesInput;

	@FindBy(id = "cheightmeter")
	private WebElement heightCmInput;

	// WEIGHT
	@FindBy(id = "ckg")
	private WebElement weightKgInput;

	@FindBy(id = "cpound")
	private WebElement weightLbsInput;

	// ACTIVITY
	@FindBy(id = "cactivity")
	private WebElement activityDropdown;

	// SETTINGS
	@FindBy(xpath = "//a[contains(@onclick, 'cshmoreoption(1)') and text()='+ Settings']")
	private WebElement settingsLink;

	@FindBy(xpath = "//label[@for='cformula1']")
	private WebElement bmrFormulaMifflinRadioButton;

	@FindBy(xpath = "//label[@for='cformula2']")
	private WebElement bmrFormulaKatchMcArdleRadioButton;

	@FindBy(name = "cfatpct")
	private WebElement bodyFatPercentageInput;

	// BUTTONS
	@FindBy(xpath = "//input[@type='submit' and @value='Calculate']")
	private WebElement calculateButton;

	@FindBy(xpath = "//input[@type='button' and @value='Clear']")
	private WebElement clearButton;

	@FindBy(css = "div[style*='error.svg']")
	private WebElement errorMessage;

	// RESULTS TABLE

	@FindBy(css = "div#content table tbody tr")
	private List<WebElement> resultRows;

	// *************************** METHODS *************************** //

	public CarbohydrateCalculatorPage enterAge(String age) {
		actions.type(ageInput, age);
		return this;
	}

	public CarbohydrateCalculatorPage selectMifflinStJeorAsBMR() {
		actions.click(settingsLink);
		actions.click(bmrFormulaMifflinRadioButton);
		return this;
	}

	public CarbohydrateCalculatorPage selectKatchMcArdleAsBMR() {
		actions.click(settingsLink);
		actions.click(bmrFormulaKatchMcArdleRadioButton);
		actions.isVisible(bodyFatPercentageInput);
		return this;
	}

	public CarbohydrateCalculatorPage enterBodyFatPercentage(String percentage) {
		actions.type(bodyFatPercentageInput, percentage);
		return this;
	}

	public CarbohydrateCalculatorPage selectGender(String gender) {
		if (gender.equalsIgnoreCase("male")) {
			actions.click(maleGenderRadioBtn);
		} else if (gender.equalsIgnoreCase("female")) {
			actions.click(femaleGenderRadioBtn);
		} else {
			throw new IllegalArgumentException("Invalid gender: " + gender + ". Use 'male' or 'female'.");
		}
		return this;
	}

	public CarbohydrateCalculatorPage enterHeightUS(String feet, String inches) {
		actions.type(heightFeetInput, feet);
		actions.type(heightInchesInput, inches);
		return this;
	}

	public CarbohydrateCalculatorPage enterWeightUS(String weight) {
		actions.type(weightLbsInput, weight);
		return this;
	}

	public CarbohydrateCalculatorPage enterHeightMetric(String cm) {
		actions.type(heightCmInput, cm);
		return this;
	}

	public CarbohydrateCalculatorPage enterWeightMetric(String kg) {
		actions.type(weightKgInput, kg);
		return this;
	}

	public CarbohydrateCalculatorPage selectActivity(String activity) {
		actions.selectByVisibleText(activityDropdown, activity);
		return this;
	}

	public CarbohydrateCalculatorPage clickCalculateButton() {
		actions.click(calculateButton);
		actions.waitForPageToLoad();
		return this;
	}

	public CarbohydrateCalculatorPage clickClearButton() {
		actions.click(clearButton);
		return this;
	}

	public CarbohydrateCalculatorPage clickAway() {
		actions.click(By.tagName("body"));
		return this;
	}

	public CarbohydrateCalculatorPage clickUSUnits() {
		actions.click(usUnitsLink);
		actions.waitForPageToLoad();
		return this;
	}

	public CarbohydrateCalculatorPage clickMetricUnits() {
		actions.click(metricUnitsLink);
		actions.waitForPageToLoad();
		return this;
	}

	public boolean isAgeErrorMessageDisplayed(String expectedMessage) {
		String actualMessage = getDynamicErrorMessageText("cage");
		return actualMessage.contains(expectedMessage);
	}

	public String getDynamicErrorMessageText(String inputId) {
		try {
			String errorDivId = inputId + "ifcErr";
			WebElement errorDiv = driver.findElement(By.id(errorDivId));

			if (actions.isVisible(errorDiv)) {
				return actions.getText(errorDiv).trim();
			}
		} catch (NoSuchElementException e) {

		}
		return "";
	}

	public boolean isErrorMessageHidden() {
		try {
			return !errorMessage.isDisplayed();
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return true;
		}
	}

	public Map<String, Map<String, String>> getResultsTable() {
		Map<String, Map<String, String>> resultMap = new HashMap<>();

		for (WebElement row : resultRows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			if (cells.size() >= 4) {
				String goal = cells.get(0).getText().trim();

				String calories = cells.get(1).getText();
				String carb40 = cells.get(2).getText();
				String carb55 = cells.get(3).getText();

				Map<String, String> rowData = new HashMap<>();
				rowData.put("Calories", calories);
				rowData.put("Carb 40% (grams)", carb40);
				rowData.put("Carb 55% (grams)", carb55);

				resultMap.put(goal, rowData);
			}
		}

		return resultMap;
	}

	public String getAgeErrorMessageText() {
		actions.isVisible(errorMessage);
		return errorMessage.getAttribute("textContent").trim();
	}

}
