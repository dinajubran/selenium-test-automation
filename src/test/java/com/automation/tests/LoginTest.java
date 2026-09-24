package com.automation.tests;

import com.automation.pages.DashboardPage;
import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest {
    final String LOGIN_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    @Test
    public void testLoginWithValidUsernameAndValidPassword(){
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.open(LOGIN_URL).enterUsername("Admin").enterPassword("admin123").clickLoginSuccess();
        Assert.assertTrue(dashboardPage.isDisplayed());
    }

    @Test
    public void testLoginWithValidUsernameAndInvalidPassword(){
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open(LOGIN_URL).enterUsername("Admin").enterPassword("admin1234").clickLoginExpectingFailure();
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid credentials");
    }

    @Test
    public void testLoginWithInvalidUsernameAndInvalidPassword(){
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open(LOGIN_URL).enterUsername("AdminUser").enterPassword("admin1234").clickLoginExpectingFailure();
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid credentials");
    }
}
