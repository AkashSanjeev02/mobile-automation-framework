package com.akash.automation.driver;

import com.akash.automation.base.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

import static com.akash.automation.base.BaseTest.*;

public class DriverFactory{

    public static AppiumDriver createDriver(String platformName) {

        AppiumDriver driver = null;

        try {

            if (platformName.equalsIgnoreCase("Android")) {
                System.out.println("Starting Android Driver Initialization...");

                UiAutomator2Options androidOptions = new UiAutomator2Options();
                androidOptions.setCapability("appium:platformName", AndroidPlatformName);
                androidOptions.setCapability("appium:automationName", AndroidAutomationName);
                androidOptions.setCapability("appium:deviceName", AndroidDeviceName.get());
                androidOptions.setCapability("appium:app", System.getProperty("user.dir")+AndroidAppPath.get());
                androidOptions.setCapability("appium:fullReset", true);
                androidOptions.setCapability("appium:noReset", false);

                if(AndroidAppPackage.get() != null && !AndroidAppPackage.get().isEmpty()){
                    androidOptions.setCapability("appium:appPackage", AndroidAppPackage.get());
                    androidOptions.setCapability("appium:appActivity", AndroidAppActivity.get());
                }

                driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), androidOptions);

            } else if (platformName.equalsIgnoreCase("iOS")) {
                System.out.println("Starting iOS Driver Initialization...");

                XCUITestOptions iOSOptions = new XCUITestOptions();
                iOSOptions.setCapability("platformName", "iOS");
                iOSOptions.setCapability("automationName", "XCUITest");
                iOSOptions.setCapability("deviceName", "iPhone 15");
                iOSOptions.setCapability("platformVersion", "17.0");
                iOSOptions.setCapability("app", System.getProperty("user.dir"));
                iOSOptions.setCapability("fullReset", true);
                iOSOptions.setCapability("noReset", false);

                if(iOSAppPackage.get() != null && !iOSAppPackage.get().isEmpty()){
                    iOSOptions.setCapability("appPackage", iOSAppPackage.get());
                    iOSOptions.setCapability("appActivity", iOSAppActivity.get());
                }

                driver = new IOSDriver(new URL("http://127.0.0.1:4723"), iOSOptions);
            }

        }
        catch (Exception e) {
                throw new RuntimeException("Driver creation failed", e);
        }

        return driver;
    }
}
