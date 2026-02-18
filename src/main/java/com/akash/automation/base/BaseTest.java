package com.akash.automation.base;

import com.akash.automation.driver.DriverFactory;
import com.akash.automation.driver.DriverManager;
import io.appium.java_client.AppiumDriver;

public class BaseTest {

//    Android Specific Capabilities
    public static final String AndroidAutomationName = "UiAutomator2";
    public static final String AndroidPlatformName = "Android";
    public static ThreadLocal<String> AndroidDeviceName = new ThreadLocal<>();
    public static ThreadLocal<String> AndroidPlatformVersion = new ThreadLocal<>();
    public static ThreadLocal<String> AndroidAppPath = new ThreadLocal<>();
    public static ThreadLocal<String> AndroidAppPackage = new ThreadLocal<>();
    public static ThreadLocal<String> AndroidAppActivity = new ThreadLocal<>();

//   iOS Specific Capabilities
    public static final String iOSAutomationName = "XCUITest";
    public static final String iOSPlatformName = "iOS";
    public static ThreadLocal<String> iOSDeviceName = new ThreadLocal<>();
    public static ThreadLocal<String> iOSPlatformVersion = new ThreadLocal<>();
    public static ThreadLocal<String> iOSAppPath = new ThreadLocal<>();
    public static ThreadLocal<String> iOSAppPackage = new ThreadLocal<>();
    public static ThreadLocal<String> iOSAppActivity = new ThreadLocal<>();

    /* ================== COMMON STATIC ELEMENTS ================== */

    /* ================== DRIVER INITIALIZATION ================== */

    public static void initializeDriver(String platform) {
        AppiumDriver driver = DriverFactory.createDriver(platform);
        DriverManager.setDriver(driver);
    }

}
