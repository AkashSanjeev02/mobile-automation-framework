package com.akash.automation.driver;

import io.appium.java_client.AppiumDriver;

public class DriverManager {

    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static void setDriver(AppiumDriver driverInstance) {
        driver.set(driverInstance);
    }

    public static AppiumDriver getDriver() {
        if (driver.get() == null) {
            throw new RuntimeException("Driver is NULL. Driver not initialized.");
        }
        return driver.get();
    }

}
