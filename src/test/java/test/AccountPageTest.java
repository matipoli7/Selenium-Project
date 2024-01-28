package test;

import PageObject.AccountPage;
import PageObject.HomePage;
import PageObject.LoginPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.TestBase;
import reports.Reports;

import static helpers.GlobalMethods.takeScreenshot;

public class AccountPageTest extends TestBase {
    HomePage homePage;
    LoginPage loginPage;
    AccountPage accountPage;

    @BeforeMethod
    public void setUp() {
        initialization();
        homePage = new HomePage();
        loginPage = new LoginPage();
        accountPage = new AccountPage();
        loginPage = homePage.goToLoginPage();
        accountPage = loginPage.login(testdata.getProperty("userLogin"), testdata.getProperty("userPassword"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshot(driver, result);
    }

    @Test(priority = 0)
    public void verifyNameAccountAfterLogin() {
        Assert.assertEquals(accountPage.getNameAccount(), testdata.getProperty("correctNameAccount"),
                "Nie jesteś zalogowany na właściwe konto! Proszę o wylogowanie się");
    }
}