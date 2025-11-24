package tests;

import base.BaseTest;
import pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {

        test.info("Starting Login Test");

        driver.get("https://www.saucedemo.com/");
        test.pass("Navigated to login page");

        LoginPage login = new LoginPage(driver);

        login.enterUsername("standard_user");
        test.pass("Entered username");

        login.enterPassword("secret_sauce");
        test.pass("Entered password");

        login.clickLogin();
        test.pass("Clicked login");

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
        test.pass("Login successful");
    }


}
