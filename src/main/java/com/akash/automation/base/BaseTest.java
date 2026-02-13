package com.akash.automation.base;

import com.akash.automation.driver.DriverFactory;
import com.akash.automation.driver.DriverManager;
import io.appium.java_client.AppiumDriver;

public class BaseTest {

//    Android Specific Capabilities
    public static final String AndroidAutomationName = "UiAutomator2";
    public static final String AndroidPlatformName = "Android";
    public static String AndroidAppPath;
    public static String AndroidDeviceName;
    public static String AndroidPlatformVersion;
    public static String AndroidAppPackage;
    public static String AndroidAppActivity;


//   iOS Specific Capabilities
    public static final String iOSAutomationName = "XCUITest";
    public static final String iOSPlatformName = "iOS";
    public static String iOSAppPath;
    public static String iOSDeviceName;
    public static String iOSPlatformVersion;
    public static String iOSAppPackage;
    public static String iOSAppActivity;

//    Common Utilities

    public static void initializeDriver(String platform) {
        AppiumDriver driver = DriverFactory.createDriver(platform);
        DriverManager.setDriver(driver);
    }

    public boolean isPlatformAndroid() {
        return AndroidPlatformName.equalsIgnoreCase("Android");
    }
    public boolean isPlatformIos() {
        return iOSPlatformName.equalsIgnoreCase("iOS");
    }

}
