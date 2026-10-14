package com.automation.tests;

import com.automation.driver.DriverFactory;
import com.automation.driver.DriverManager;
import com.automation.config.FrameworkConfig;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseTest {
    protected FrameworkConfig config;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("") String browser) {
        config = FrameworkConfig.load();
        String selectedBrowser = browser.isBlank() ? config.browser() : browser;
        WebDriver driver = DriverFactory.createDriver(selectedBrowser, config.headless());
        DriverManager.setDriver(driver);
    }

    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver driver = DriverManager.getDriver();
        try {
            if (driver != null) {
                driver.quit();
            }
        } finally {
            DriverManager.unload();
        }
    }
}
