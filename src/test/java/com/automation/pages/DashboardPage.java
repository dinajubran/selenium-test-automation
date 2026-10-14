package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class DashboardPage extends BasePage{

    private static final By DASHBOARD_HEADING = By.xpath("//h6[normalize-space()='Dashboard']");

    public DashboardPage(WebDriver driver, Duration timeout){
        super(driver, timeout);
    }

    public boolean isDisplayed(){
    try {
        return waitAndFind(DASHBOARD_HEADING).isDisplayed();
    } catch (TimeoutException e){
        return false;
    }
    }
}
