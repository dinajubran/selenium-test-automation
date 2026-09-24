package com.automation.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class DriverFactory {

    private DriverFactory() {}

    public static WebDriver createDriver(String browser) {
        switch (browser.toLowerCase().trim()) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                return new FirefoxDriver(firefoxOptions);

            case "chrome":
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications", "--start-maximized");
                // chromeOptions.addArguments("--headless=new"); // Ideal for CI/CD
                return new ChromeDriver(chromeOptions);
        }
    }
}