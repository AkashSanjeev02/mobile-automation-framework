Feature: HomePage

  Scenario Outline: All Alerts Validate
    Given Launch the application and verify the General Store page is displayed
    When Select the country : "<Country>"
    And Enter the name : "<Name>"
    And Select the gender : "<Gender>"
    Then Click on the Let's Shop button
    Then Verify the presence of Product Catalog Page

    Examples:
      | Country | Name  | Gender |
      | India   | Akash | Male   |