package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginWithValidUsernameAndValidPassword(){
        final String LOGIN_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();
        Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard/index"));
    }

    @Test
    public void testLoginWithValidUsernameAndInvalidPassword(){
        final String LOGIN_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin1234");
        loginPage.clickLogin();
        Assert.assertEquals(loginPage.getLoginError(), "Invalid credentials");
    }

    @Test
    public void testLoginWithInvalidUsernameAndInvalidPassword(){
        final String LOGIN_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("AdminUser");
        loginPage.enterPassword("admin1234");
        loginPage.clickLogin();
        Assert.assertEquals(loginPage.getLoginError(), "Invalid credentials");
    }
}
