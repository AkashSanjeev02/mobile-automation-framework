package com.akash.automation.utils;

import com.akash.automation.base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GenericUtility extends BaseTest {
    private AppiumDriver driver;
    private WebDriverWait wait;

    public GenericUtility(AppiumDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public boolean isPlatformAndroid() {
        return driver.getCapabilities().getPlatformName().toString().equalsIgnoreCase("Android");
    }

    public boolean isPlatformIos() {
        return driver.getCapabilities().getPlatformName().toString().equalsIgnoreCase("iOS");
    }


    /* ================== CUSTOM WAITS ================== */
    public void waitForElement(WebElement element, int duration) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForClickable(WebElement element, int duration) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForInvisibility(WebElement element, int duration) {
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

    /* --------------------------------------------------
       1️⃣ TAP (W3C)
    -------------------------------------------------- */
    public void tap(WebElement element) {
        Point point = element.getLocation();

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);

        tap.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), point.x, point.y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(tap));
    }

    /* --------------------------------------------------
       2️⃣ LONG PRESS (W3C)
    -------------------------------------------------- */
    public void longPress(WebElement element, int seconds) {
        Point point = element.getLocation();

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence longPress = new Sequence(finger, 1);

        longPress.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), point.x, point.y));
        longPress.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        longPress.addAction(new Pause(finger, Duration.ofSeconds(seconds)));
        longPress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(longPress));
    }

    /* --------------------------------------------------
       3️⃣ SWIPE (W3C)
    -------------------------------------------------- */
    public void swipe(int startX, int startY, int endX, int endY) {

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600),
                PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    /* --------------------------------------------------
       3️⃣ SWIPE Vertical(W3C)
    -------------------------------------------------- */
    public void swipeVertical(int startYPercent, int endYPercent) {
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;

        int startY = (int) (size.height * (startYPercent / 100.0));
        int endY = (int) (size.height * (endYPercent / 100.0));

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), x, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    /* --------------------------------------------------
       4️⃣ SCROLL (W3C - Screen based)
    -------------------------------------------------- */
    public void scrollDown() {
        Dimension size = driver.manage().window().getSize();

        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY   = (int) (size.height * 0.2);

        swipe(startX, startY, startX, endY);
    }

    /* --------------------------------------------------
       5️⃣ iOS SCROLL TO TEXT (mobile: scroll)
       ✅ Recommended over W3C
    -------------------------------------------------- */
    public void scrollToTextIOSAndClick(String text) {
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "down");
        params.put("predicateString", "label == '" + text + "'");

        driver.executeScript("mobile: scroll", params);
    }

    /* --------------------------------------------------
       6️⃣ ANDROID SCROLL TO TEXT (UiAutomator)
       ✅ Best for Android
    -------------------------------------------------- */
    public void scrollToTextAndroidAndClick(String text) {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"" + text + "\"))")).click();
    }

    /* --------------------------------------------------
       7️⃣ DRAG & DROP (W3C)
    -------------------------------------------------- */
    public void dragAndDrop(WebElement source, WebElement target) {

        Point src = source.getLocation();
        Point tgt = target.getLocation();

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragDrop = new Sequence(finger, 1);

        dragDrop.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), src.x, src.y));
        dragDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragDrop.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(), tgt.x, tgt.y));
        dragDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(dragDrop));
    }

    /* --------------------------------------------------
       8️⃣ PINCH / ZOOM (W3C - ADVANCED)
    -------------------------------------------------- */
    public void pinchZoom(int centerX, int centerY, boolean zoom) {

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

        int offset = zoom ? 100 : 20;

        Sequence seq1 = new Sequence(finger1, 1);
        Sequence seq2 = new Sequence(finger2, 1);

        seq1.addAction(finger1.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), centerX, centerY - offset));
        seq2.addAction(finger2.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), centerX, centerY + offset));

        seq1.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        seq2.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        seq1.addAction(finger1.createPointerMove(Duration.ofMillis(600),
                PointerInput.Origin.viewport(), centerX, centerY - 10));
        seq2.addAction(finger2.createPointerMove(Duration.ofMillis(600),
                PointerInput.Origin.viewport(), centerX, centerY + 10));

        seq1.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        seq2.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(seq1, seq2));
    }
}
