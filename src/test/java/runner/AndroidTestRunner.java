package runner;

import com.akash.automation.base.BaseTest;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.io.File;

@CucumberOptions
        (
                features = "src/test/resources/features",
//                tags = "@login-success",
                glue = {"com.akash.automation.stepdefinitions", "com.akash.automation.hooks", "com.akash.automation.utils", "com.akash.automation.base", "com.akash.automation.driver"},
                plugin = {"pretty", "html:target/cucumber-reports.html"},
                monochrome = true
        )
public class AndroidTestRunner extends AbstractTestNGCucumberTests {

        @Parameters({"deviceName","appFileName", "appPackage", "appActivity", "platformVersion"})
        @BeforeSuite(alwaysRun = true)
        public void beforeSuite(String deviceName, String appFileName, String appPackage, String appActivity, String platformVersion) {
                // Assign all parameters to the BaseClass instance
                BaseTest.AndroidDeviceName.set(deviceName);
                BaseTest.AndroidAppPath.set(appFileName);
                BaseTest.AndroidAppPackage.set(appPackage);
                BaseTest.AndroidAppActivity.set(appActivity);
                BaseTest.AndroidPlatformVersion.set(platformVersion);

                if (appFileName != null && !appFileName.isEmpty()) {
                        File apkFile = new File(System.getProperty("user.dir") + appFileName);
                        if (apkFile.exists()) {
                                System.out.println("Launching with APK.");
                        }
                } else {
                        System.out.println("Launching installed app on real device.");
                }
                BaseTest.initializeDriver("Android");
        }

        @Override
        @DataProvider(parallel = false)
        public Object[][] scenarios() {
                return super.scenarios();
        }
}
