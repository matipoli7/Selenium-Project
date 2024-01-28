package PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
public class mmm {

        public static WebDriver driver;

        @Test
        public void WebElementyTest() throws InterruptedException {

            //Konfiguracja początkowa
            System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");

            driver = new ChromeDriver();

            //Krok 1
            driver.get("http://www.selenium-shop.pl/");

            driver.manage().window().maximize();

            //Krok 2
            WebElement ankietaMenu = driver.findElement(By.linkText("ANKIETA"));
            ankietaMenu.click();

            //Krok 3
            WebElement imie = driver.findElement(By.id("Imię"));
            imie.clear();
            imie.sendKeys("Tomasz");

            //Krok 4
            WebElement mezczyznaRadioButton = driver.findElement(By.xpath("//input[@name='KobietaCzyMezczyzna'][@value='Mężczyzna']"));
            if(!mezczyznaRadioButton.isSelected()){
                mezczyznaRadioButton.click();
            }

            //Krok 5
            WebElement przedzialWieku = driver.findElement(By.xpath("//input[@name='przedzialWieku'][@value='20-29']"));
            if(!przedzialWieku.isSelected()){
                przedzialWieku.click();
            }

            //Krok 6
            WebElement komentarz = driver.findElement(By.id("Komentarz"));
            komentarz.clear();
            komentarz.sendKeys("Warszawa");

            //Krok 7
            WebElement wyslijPrzycisk = driver.findElement(By.id("Wyslij"));

            if(wyslijPrzycisk.isDisplayed()){
                System.out.println("Przycisk WYSLIJ jest wyświetlony na stronie");
            }else
            {
                System.out.println("Przycisk WYSLIJ nie jest wyświetlony na stronie");
            }

            //Krok 8
            wyslijPrzycisk.click();

            //Krok 9
            WebElement wyslaneInformacje = driver.findElement(By.id("info"));
            System.out.println(wyslaneInformacje.getText());

            //Zamknięcie przeglądarki
            driver.close();

        }
    }

