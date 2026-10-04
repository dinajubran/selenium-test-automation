package com.automation.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class DriverFactory {

    static String[] browserOptions = {"--disable-notifications", "--headless=new", "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1920,1080"};
    private DriverFactory() {}

    public static WebDriver createDriver(String browser) {
        switch (browser.toLowerCase().trim()) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments(browserOptions);
                return new FirefoxDriver(firefoxOptions);

            case "chrome":
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(browserOptions);
                return new ChromeDriver(chromeOptions);
        }
    }
}