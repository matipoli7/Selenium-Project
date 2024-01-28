package test;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.*;
import PageObject.*;
import base.*;

import java.net.MalformedURLException;


public class OrderConfirmedPageTest extends TestBase {
    HomePage homePage;
    LoginPage loginPage;
    AccountPage accountPage;
    OrderConfirmedPage orderConfirmedPage;
    /********* konstruktor ***********/
    public OrderConfirmedPageTest() {
        super();
    }


    // Przed każdym testem uruchomienie przeglądarki wraz z zadeklarowanymi własnościami
    @BeforeMethod
    public void setUp() throws MalformedURLException {
        initialization();
        homePage = new HomePage();
        loginPage = new LoginPage();
        accountPage = new AccountPage();
        orderConfirmedPage = new OrderConfirmedPage();
        loginPage = homePage.goToLoginPage();
        accountPage = loginPage.login(testdata.getProperty("userLogin"), testdata.getProperty("userPassword"));
        accountPage.goToOrders();

    }
        // Po każdym teście zamknięcie przeglądarki


        /*********  TESTY *********/
        @Test(priority = 0)
        public void verifyNoOrders () {
            Assert.assertFalse(orderConfirmedPage.getInfoAboutOrders().contains(testdata.getProperty("correctInfoAboutOrder")),
                    "Istnieją już złożone zamówienia!");


    }
}