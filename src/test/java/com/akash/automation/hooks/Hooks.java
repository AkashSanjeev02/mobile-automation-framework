package com.akash.automation.hooks;

import com.akash.automation.base.BaseTest;
import com.akash.automation.driver.DriverManager;
import com.akash.automation.utils.GenericUtility;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.CucumberOptions;
import runner.AndroidTestRunner;

public class Hooks extends BaseTest{
    @Before
    public void beforeScenario() {
    }

    @After
    public void afterScenario(){
        if(DriverManager.getDriver() != null){
            DriverManager.getDriver().quit();
        }
    }

}
