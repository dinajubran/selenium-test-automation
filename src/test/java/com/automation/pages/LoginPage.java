package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;

    private final String USERNAME_FIELD = "input[placeholder='Username']";
    private final String PASSWORD_FIELD = "input[placeholder='Password']";
    private final String LOGIN_BUTTON = "button[type='submit']";
    private final String LOGIN_ERROR = "div[role='alert']";


    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterUsername(String username){
        driver.findElement(By.cssSelector(USERNAME_FIELD)).sendKeys(username);
    }

    public void enterPassword(String password){
        driver.findElement(By.cssSelector(PASSWORD_FIELD)).sendKeys(password);
    }

    public void clickLogin(){
        driver.findElement(By.cssSelector(LOGIN_BUTTON)).click();
    }

    public String getLoginError(){
        return driver.findElement(By.cssSelector(LOGIN_ERROR)).getText();
    }



}
