package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    private final By USERNAME_FIELD = By.cssSelector("input[placeholder='Username']");
    private final By PASSWORD_FIELD = By.cssSelector("input[placeholder='Password']");
    private final By LOGIN_BUTTON = By.cssSelector("button[type='submit']");
    private final By LOGIN_ERROR = By.cssSelector("div[role='alert']");


    public LoginPage(WebDriver driver){
        super(driver);
    }

    public LoginPage open(String url) {
        driver.get(url);
        return this;
    }

    public LoginPage enterUsername(String username){
        type(USERNAME_FIELD, username);
        return this;
    }

    public LoginPage enterPassword(String password){
        type(PASSWORD_FIELD, password);
        return this;
    }

    public DashboardPage clickLoginSuccess(){
        click(LOGIN_BUTTON);
        return new DashboardPage(driver);
    }

    public LoginPage clickLoginExpectingFailure(){
        click(LOGIN_BUTTON);
        return this;
    }

    public String getErrorMessage(){
        return getText(LOGIN_ERROR);
    }



}
