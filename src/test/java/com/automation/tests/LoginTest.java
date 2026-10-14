package com.automation.tests;

import com.automation.pages.DashboardPage;
import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;


public class LoginTest extends BaseTest {

    @Test
    public void testLoginWithValidUsernameAndValidPassword(){
        LoginPage loginPage = new LoginPage(getDriver(), config.timeout());
        DashboardPage dashboardPage = loginPage.open(config.loginUrl())
                .loginExpectingSuccess(config.adminUsername(), config.adminPassword());
        Assert.assertTrue(dashboardPage.isDisplayed(), "Dashboard should be displayed after a valid login");
    }

    @Test
    public void testLoginWithValidUsernameAndInvalidPassword(){
        LoginPage loginPage = new LoginPage(getDriver(), config.timeout());
        loginPage.open(config.loginUrl())
                .loginExpectingFailure(config.adminUsername(), "invalid-" + UUID.randomUUID());
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid credentials",
                "A valid username with an invalid password should be rejected");
    }

    @Test
    public void testLoginWithInvalidUsernameAndInvalidPassword(){
        LoginPage loginPage = new LoginPage(getDriver(), config.timeout());
        loginPage.open(config.loginUrl())
                .loginExpectingFailure("invalid-" + UUID.randomUUID(), "invalid-" + UUID.randomUUID());
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid credentials",
                "Invalid credentials should be rejected");
    }
}
