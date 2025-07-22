Feature: Carbohydrate Calculator

  Scenario: TC0033 - Verify Mifflin St Jeor method calculation engine
    Given I navigate to Carbohydrate Calculator page
    When I select US Units
    And I enter the following details in the input table:
      | Field          | Value   |
      | Age            | 35      |
      | Gender         | female  |
      | Height (feet)  | 5       |
      | Height (inches)| 1       |
      | Weight (lbs)   | 110     |
      | Activity       | Moderate: exercise 4-5 times/week|
    And I select Mifflin St Jeor as the BMR estimation formula
    And I click on the Calculate button
    Then The results table should contain carb gram value 177
    

  Scenario: TC0034 - Verify Katch-McArdle method calculation engine
    Given I navigate to Carbohydrate Calculator page
    When I select Metric Units
  	And I enter the following details in the input table:
      | Field    | Value   |
      | Age      | 35      |
      | Gender   | female  |
      | Height (cm)| 150     |
      | Weight (kg)| 50      |
      | Activity | Active: daily exercise or intense exercise 3-4 times/week  |
    And I select Katch-McArdle as the BMR estimation formula
    And I enter percent body fat as "25"
    And I click on the Calculate button
    Then The results table should contain carb gram value 195
    
    
  Scenario: TC0024 - Verify calculation with out of lower bound age
    Given I navigate to Carbohydrate Calculator page
    When I enter value for Age as "-1"
    Then An error message appears for Age with text "positive numbers only"
    When I enter value for Age as "17"
    Then No error messages should be displayed
    When I click on the Calculate button
    Then An error message appears on screen with text "Please provide an age between 18 and 80"
