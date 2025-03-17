package com.arnest.common;

import com.arnest.driver.DriverManager;
import com.arnest.driver.TargetFactory;
import com.arnest.helpers.PropertiesHelpers;
import com.arnest.keywords.WebUI;
import com.arnest.listeners.TestListener;
//import com.arnest.projects.cms.CommonPageCMS;
import com.arnest.project.unify.CommonPageUnify;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.*;

import java.time.Duration;

@Listeners({TestListener.class})
public class BaseTest extends CommonPageUnify {

    @Parameters("BROWSER")
    @BeforeMethod(alwaysRun = true)
    public void createDriver(@Optional("chrome") String browser) {
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
//                .window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        WebUI.stopSoftAssertAll();
        DriverManager.quit();
    }

    public WebDriver createBrowser(@Optional("chrome") String browser) {
        PropertiesHelpers.loadAllFiles();
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        driver.manage().window().maximize();
        DriverManager.setDriver(driver);
        return DriverManager.getDriver();
    }

}
