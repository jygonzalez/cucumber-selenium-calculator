# What is this project?
This is an automation test framework that uses Selenium, TestNG (with parallel execution) and Cucumber (BDD) for testing https://www.calculator.net/carbohydrate-calculator.html. Note that only a few scenarios have been added. Complexity of steps (gherkin and definitions) to be refactored.

# How can you run it?
## Prerequisites
- Install Java JDK (recommend JDK >= 17)
- Install Browsers: Chrome, Edge or Firefox (you can modify the browser in the config file, found in `./src/test/resources/config/config.properties`)

## Instructions
1. Install prerequisites
2. Clone project and import to Eclipse as Maven Project
3. Then Run As `Maven Test`

### Generate Allure Report
After running the tests, the results should be saved into the `./target/allure-results` directory. To generate the report:
- If you have `Allure CLI` installed, just navigate to `./target` and run `allure serve`
- If you have Maven installed you can do `mvn allure:serve` instead, which will configure a local allure for the project in `./.allure` 
  - Or, if you have Eclipse, you can configure a new `Runtime Configuration` for Maven, and use the embedded Maven Runtime available in Eclipse
    <img width="912" height="836" alt="image" src="https://github.com/user-attachments/assets/3517d081-5acc-420f-b1bd-58d32b168b06" />

# Directory
- Features (tests): `./src/test/resources/features`
- Step Definitions (binding code for test steps): `./src/test/java/stepdefinitions`
- Pages (code for web UI elements): `./src/test/java/pages`
