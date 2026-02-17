package com.akash.automation.pages;

import com.akash.automation.base.BaseTest;
import com.akash.automation.utils.GenericUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class GeneralStorePage extends BaseTest {

    GenericUtility genericUtility;

    /* ================== Initialization of driver and page factory ================== */
    AppiumDriver driver;
    ;

    public GeneralStorePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        genericUtility = new GenericUtility(driver);
    }

    public static GeneralStorePage getInstance(AppiumDriver driver) {
        GeneralStorePage generalStorePage;
        synchronized (GeneralStorePage.class) {
            generalStorePage = new GeneralStorePage(driver);
        }
        return generalStorePage;
    }

    /* ================== WEB ELEMENTS ================== */
    @AndroidFindBy(id = "android:id/text1")
    WebElement countryDropdown;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    WebElement nameField;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
    WebElement maleRadio;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
    WebElement femaleRadio;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    WebElement letsShopButton;

    /* ================== ACTIONS ================== */
    public void verifyThePresenceOfGeneralStorePage() {
        genericUtility.waitForElementReturnTrueElseFalse(countryDropdown, 20);
    }

    public void selectCountry(String country) {
        countryDropdown.click();
        if (genericUtility.isPlatformAndroid()) {
            genericUtility.scrollToTextAndroidAndClick(country);
        } else {
            genericUtility.scrollToTextIOSAndClick(country);
        }
    }

    public void enterTheName(String name) {
        genericUtility.waitForElement(nameField, 20);
        nameField.sendKeys(name);
    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Male")) {
            maleRadio.click();
        } else {
            femaleRadio.click();
        }
    }

    public void clickLetsShopButton() {
        letsShopButton.click();
    }
}
