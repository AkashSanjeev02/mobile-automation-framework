package com.akash.automation.stepdefinitions;

import com.akash.automation.driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.akash.automation.pages.GeneralStorePage;

public class GeneralStoreSteps {

    AppiumDriver driver = DriverManager.getDriver();

    @Given("Launch the application and verify the General Store page is displayed")
    public void launch_the_application() {
        GeneralStorePage.getInstance(driver).verifyThePresenceOfGeneralStorePage();
    }

    @When("Select the country : {string}")
    public void select_the_country(String Country) {
        System.out.println("Selected country: " + Country);
        GeneralStorePage.getInstance(driver).selectCountry(Country);
    }

    @When("Enter the name : {string}")
    public void enter_the_name(String Name) {
        System.out.println("Entered name: " + Name);
        GeneralStorePage.getInstance(driver).enterTheName(Name);
    }

    @When("Select the gender : {string}")
    public void select_the_gender(String Gender) {
        System.out.println("Selected"+ Gender);
        GeneralStorePage.getInstance(driver).selectGender(Gender);
    }

    @Then("Click on the Let's Shop button")
    public void click_on_the_let_s_shop_button() {
        System.out.println("Clicked on Let's Shop button");
        GeneralStorePage.getInstance(driver).clickLetsShopButton();
    }

    @Then("Verify the presence of Product Catalog Page")
    public void verify_the_presence_of_product_catalog_page() {
        System.out.println("Verified the presence of Product Catalog Page");
    }

}
