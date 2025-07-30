package runners;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@Test
@CucumberOptions(features = "src/test/resources/features", glue = { "stepdefinitions", "hooks", "context" }, plugin = {
		"pretty", "html:target/cucumber-reports/cucumber.html", "json:target/cucumber-reports/cucumber.json",
		"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" })

public class TestRunner extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
