package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage{

    private final By MAIN_CONTENT_AREA = By.cssSelector(".oxd-layout-context");

    public DashboardPage(WebDriver driver){
        super(driver);
    }

    public boolean isDisplayed(){
    try {
        return waitAndFind(MAIN_CONTENT_AREA).isDisplayed();
    } catch (TimeoutException e){
        return false;
    }
    }
}
