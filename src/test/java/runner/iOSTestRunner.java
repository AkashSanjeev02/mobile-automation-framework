package runner;

import com.akash.automation.base.BaseTest;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import java.io.File;
import java.net.MalformedURLException;

@CucumberOptions
        (
                features = "src/test/resources/features",
//                tags = "@login-success",
                glue = {"com.akash.automation.stepdefinitions", "com.akash.automation.hooks", "com.akash.automation.utils", "com.akash.automation.base", "com.akash.automation.driver"},
                plugin = {"pretty", "html:target/cucumber-reports.html"},
                monochrome = true
        )
public class iOSTestRunner extends AbstractTestNGCucumberTests {

    @Parameters({"deviceName","appFileName", "appPackage", "appActivity", "platformVersion"})
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(String deviceName, String appFileName, String appPackage, String appActivity, String platformVersion) {
        // Assign all parameters to the BaseClass instance
        BaseTest.iOSDeviceName.set(deviceName);
        BaseTest.iOSAppPath.set(appFileName);
        BaseTest.iOSAppPackage.set(appPackage);
        BaseTest.iOSAppActivity.set(appActivity);
        BaseTest.iOSPlatformVersion.set(platformVersion);

        if (appFileName != null && !appFileName.isEmpty()) {
            File apkFile = new File(System.getProperty("user.dir") + appFileName);
            if (apkFile.exists()) {
                System.out.println("Launching with ipa.");
            }
        } else {
            System.out.println("Launching installed app on real device.");
        }
        BaseTest.initializeDriver("iOS");
    }

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
