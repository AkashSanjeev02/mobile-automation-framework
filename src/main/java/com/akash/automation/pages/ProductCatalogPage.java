package com.akash.automation.pages;

import com.akash.automation.utils.GenericUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class ProductCatalogPage {
    GenericUtility genericUtility;

    /* ================== Initialization of driver and page factory ================== */
    AppiumDriver driver;
    ;

    public ProductCatalogPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        genericUtility = new GenericUtility(driver);
    }

    public static ProductCatalogPage getInstance(AppiumDriver driver) {
        ProductCatalogPage productCatalogPage;
        synchronized (GeneralStorePage.class) {
            productCatalogPage = new ProductCatalogPage(driver);
        }
        return productCatalogPage;
    }

}
