package com.automation.tests;

import com.automation.driver.DriverFactory;
import com.automation.driver.DriverManager;
import com.automation.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseTest {
    public static final String LOGIN_URL = ConfigReader.get("LOGIN_URL");
    public static final String ADMIN_USERNAME = ConfigReader.get("ADMIN_USERNAME");
    public static final String ADMIN_PASSWORD = ConfigReader.get("ADMIN_PASSWORD");

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
//        String username = ConfigReader.get("ADMIN_USERNAME");
//        String password = ConfigReader.get("ADMIN_PASSWORD");
//        String url = ConfigReader.get("LOGIN_URL");

        if (LOGIN_URL == null || ADMIN_USERNAME == null || ADMIN_PASSWORD == null) {
            throw new IllegalStateException("LOGIN_URL or ADMIN_USERNAME or ADMIN_PASSWORD environment variable is not set.");
        }
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