package com.akash.automation.driver;

import com.akash.automation.base.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

import static com.akash.automation.base.BaseTest.*;

public class DriverFactory{

    public static AppiumDriver createDriver(String platformName) {

        AppiumDriver driver = null;

        try {
            DesiredCapabilities caps = new DesiredCapabilities();

            if (platformName.equalsIgnoreCase("Android")) {
                System.out.println("Starting Android Driver Initialization...");
                caps.setCapability("appium:platformName", AndroidPlatformName);
                caps.setCapability("appium:automationName", AndroidAutomationName);
                caps.setCapability("appium:deviceName", AndroidDeviceName);
                caps.setCapability("appium:app", System.getProperty("user.dir")+AndroidAppPath);
                caps.setCapability("appium:fullReset", true);
                caps.setCapability("appium:noReset", false);

                driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);

            } else if (platformName.equalsIgnoreCase("iOS")) {
                System.out.println("Starting iOS Driver Initialization...");
                caps.setCapability("platformName", "iOS");
                caps.setCapability("automationName", "XCUITest");
                caps.setCapability("deviceName", "iPhone 15");
                caps.setCapability("platformVersion", "17.0");
                caps.setCapability("app", System.getProperty("user.dir"));
                caps.setCapability("fullReset", true);
                caps.setCapability("noReset", false);

                driver = new IOSDriver(new URL("http://127.0.0.1:4723"), caps);
            }

        }
        catch (Exception e) {
                throw new RuntimeException("Driver creation failed", e);
        }

        return driver;
    }
}
