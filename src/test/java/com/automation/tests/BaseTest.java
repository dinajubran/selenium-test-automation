package com.automation.tests;

import com.automation.driver.DriverFactory;
import com.automation.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseTest {
    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        WebDriver driver = DriverFactory.createDriver(browser);
        DriverManager.setDriver(driver);
    }

    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            driver.quit();
            DriverManager.unload();
        }
    }
}