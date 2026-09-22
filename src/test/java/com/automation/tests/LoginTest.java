package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.DashboardPage;
import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    final String LOGIN_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    @Test
    public void testLoginWithValidUsernameAndValidPassword(){
        driver.get(LOGIN_URL);
        DashboardPage dashboardPage = new LoginPage(driver).enterUsername("Admin").enterPassword("admin123").clickLoginSuccess();
        Assert.assertTrue(dashboardPage.isDisplayed());
    }

    @Test
    public void testLoginWithValidUsernameAndInvalidPassword(){
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver).enterUsername("Admin").enterPassword("admin1234").clickLoginExpectingFailure();
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid credentials");
    }

    @Test
    public void testLoginWithInvalidUsernameAndInvalidPassword(){
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver).enterUsername("AdminUser").enterPassword("admin1234").clickLoginExpectingFailure();
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid credentials");
    }
}
