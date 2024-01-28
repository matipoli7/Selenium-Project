package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class kole {
    public static WebDriver driver;
        @Test
        public void konmie() {

            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();

            driver.get("https://pl.wikipedia.org/");
            String tag = driver.findElement(By.id("main-page-content")).getTagName();
            System.out.println(tag);
        }
    }


