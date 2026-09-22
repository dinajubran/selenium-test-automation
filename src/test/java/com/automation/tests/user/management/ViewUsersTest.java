package com.automation.tests.user.management;

import com.automation.base.BaseTest;
import com.automation.pages.LoginPage;
import org.testng.annotations.Test;

public class ViewUsersTest extends BaseTest {
    private static final String URL = "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers";

    public void loginAndNavigateToPage(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginSuccess();
        driver.get(URL);
    }

    @Test
    public void whenAdminOpenUserManagementPageHeShouldBeAbleToViewAllUsers() {
        loginAndNavigateToPage();
        System.out.println("test");

    }
}
