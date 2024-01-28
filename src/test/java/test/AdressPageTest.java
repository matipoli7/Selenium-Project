package test;


        import org.testng.Assert;
        import org.testng.annotations.*;
        import PageObject.*;
        import base.*;

        import java.net.MalformedURLException;

public class AdressPageTest extends TestBase{
    HomePage homePage;
    LoginPage loginPage;
    AccountPage accountPage;
    AddressPage addressPage;

    /********* konstruktor ***********/
    public AdressPageTest() {
        super();
    }

    // Przed każdym testem uruchomienie przeglądarki wraz z zadeklarowanymi własnościami
    @BeforeMethod
    public void setUp() throws MalformedURLException {
        initialization();
        homePage = new HomePage();
        loginPage = new LoginPage();
        accountPage = new AccountPage();
        addressPage = new AddressPage();

        loginPage = homePage.goToLoginPage();
        accountPage = loginPage.login(testdata.getProperty("userLogin"), testdata.getProperty("userPassword"));
        addressPage = accountPage.goToAddresses();
    }

    // Po każdym teście zamknięcie przeglądarki



    /*********  TESTY *********/
    @Test(priority = 0)
    public void verifyNoDeliveryAddress() {
        Assert.assertFalse(addressPage.getInfoDeliveryAddress().equalsIgnoreCase
                (testdata.getProperty("correctInfoAboutDeliveryAddress")));

    }

}