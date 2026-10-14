package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class LoginPage extends BasePage{

    private static final By USERNAME_FIELD = By.cssSelector("input[name='username']");
    private static final By PASSWORD_FIELD = By.cssSelector("input[name='password']");
    private static final By LOGIN_BUTTON = By.cssSelector("button[type='submit']");
    private static final By LOGIN_ERROR = By.cssSelector("div[role='alert']");


    public LoginPage(WebDriver driver, Duration timeout){
        super(driver, timeout);
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

    public DashboardPage loginExpectingSuccess(String username, String password){
        enterUsername(username);
        enterPassword(password);
        click(LOGIN_BUTTON);
        return new DashboardPage(driver, timeout);
    }

    public LoginPage loginExpectingFailure(String username, String password){
        enterUsername(username);
        enterPassword(password);
        click(LOGIN_BUTTON);
        return this;
    }

    public String getErrorMessage(){
        return getText(LOGIN_ERROR);
    }



}
