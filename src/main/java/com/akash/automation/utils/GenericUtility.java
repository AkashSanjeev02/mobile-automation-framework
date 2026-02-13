package com.akash.automation.utils;

import com.akash.automation.base.BaseTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GenericUtility extends BaseTest {
    private AppiumDriver driver;
    private WebDriverWait wait;

    public GenericUtility(AppiumDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /* ================== CUSTOM WAITS ================== */
    public void waitForElement(WebElement element, Duration duration) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForClickable(WebElement element, Duration duration) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForInvisibility(WebElement element, Duration duration) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean waitForElementReturnTrueElseFalse(WebElement element, int duration) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
