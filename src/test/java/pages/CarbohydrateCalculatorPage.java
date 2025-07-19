package pages;

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
	@FindBy(xpath = "//div[@id='topmenu']//li/a[text()='US units']")
	private WebElement usUnitsLink;

	@FindBy(xpath = "//div[@id='topmenu']//li/a[text()='metric units']")
	private WebElement metricUnitsLink;

	@FindBy(xpath = "//div[@id='topmenu']//li/a[text()='other units']")
	private WebElement otherUnitsLink;

	// AGE
	@FindBy(id = "cage")
	private WebElement ageInput;

	// GENDER
	@FindBy(id = "csex1")
	private WebElement maleGenderRadioBtn;

	@FindBy(id = "csex2")
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

	// ACTIVITY
	@FindBy(id = "cactivity")
	private WebElement activityDropdown;

	// SETTINGS
	@FindBy(xpath = "//a[contains(@onclick, 'cshmoreoption(1)') and text()='+ Settings']")
	private WebElement settingsLink;

	@FindBy(id = "cformula1")
	private WebElement bmrFormulaMifflinRadioButton;

	@FindBy(id = "cformula2")
	private WebElement bmrFormulaKatchMcArdleRadioButton;

	@FindBy(name = "cfatpct")
	private WebElement bodyFatPercentageInput;

	// BUTTONS
	@FindBy(xpath = "//input[@type='submit' and @value='Calculate']")
	private WebElement calculateButton;

	@FindBy(xpath = "//input[@type='button' and @value='Clear']")
	private WebElement clearButton;

	// *************************** METHODS *************************** //

	public CarbohydrateCalculatorPage enterAge(String age) {
		actions.type(ageInput, age);
		return this;
	}

}
