package PageObject;


        import base.TestBase;
        import com.aventstack.extentreports.ExtentReports;
        import com.aventstack.extentreports.ExtentTest;
        import com.aventstack.extentreports.Status;
        import helpers.GlobalMethods;
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.support.FindBy;
        import org.openqa.selenium.support.PageFactory;
        import org.openqa.selenium.support.ui.WebDriverWait;
        import reports.Reports;

        import java.time.Duration;

        import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
        import static reports.Reports.extentTest;


public class HomePage  extends TestBase {


    /**** repozytorium elementów ****/
    @FindBy(xpath = "//a[@href='http://www.selenium-shop.pl/sklep/']")
    private WebElement shopMenu;

    @FindBy(xpath = "//a[@href='http://www.selenium-shop.pl/moje-konto/']")
    private WebElement myAccountMenu;



    /**** konstruktor ****/
    public HomePage() {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }


    /****  metody  ****/
    // Przejście na stronę sklepu z produktami
    public ProductListPage goToProductPage() {
        wait.until(visibilityOf(shopMenu));
        shopMenu.click();
        return new ProductListPage();
    }

    // Przejście na zakładkę Moje Konto - zakładka z logowaniem
    public LoginPage goToLoginPage() {
        wait.until(visibilityOf(myAccountMenu));
        myAccountMenu.click();
        return new LoginPage();
    }
    }

