package com.akash.automation.hooks;

import com.akash.automation.base.BaseTest;
import com.akash.automation.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends BaseTest{

    @Before
    public void beforeScenario(){
        if(isPlatformAndroid()){
            initializeDriver(AndroidPlatformName);
        }else{
            initializeDriver(iOSPlatformName);
        }
    }

    @After
    public void afterScenario(){
        if(DriverManager.getDriver() != null){
            DriverManager.getDriver().quit();
        }
    }

}
