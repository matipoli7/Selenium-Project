package helpers;

import base.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.annotations.Test;
import reports.Reports;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class GlobalMethods extends TestBase {


    public GlobalMethods() {
        // Konstruktor może zawierać logikę inicjalizacyjną, ale tutaj nie ma nic do dodania.
    }

    public String getPageTitle() {
        String title = driver.getTitle();
        System.out.println("Page title: " + title);
        return title;
    }

    public void setInput(WebElement inputElement, String text) {
        wait.until(visibilityOf(inputElement));
        inputElement.clear();
        inputElement.sendKeys(text);
    }

    public void clickButton(WebElement buttonElement) {
        wait.until(elementToBeClickable(buttonElement)).click();
    }

    public String getTextFromElement(WebElement element) {
        wait.until(visibilityOf(element));
        return element.getText();
    }

    public static void takeScreenshot(WebDriver driver, ITestResult result) {
        try {
            // Tworzy katalog "screenshots" w bieżącym katalogu roboczym, jeśli nie istnieje.
            File directory = new File("screenshots");
            if (!directory.exists()) {
                directory.mkdir();
            }

            // Tworzy unikalną nazwę pliku zgodnie z nazwą testu i aktualną datą/czasem.
            String screenshotName = result.getName() + "POLI" + System.currentTimeMillis() + ".png";

            // Tworzy ścieżkę docelową dla zrzutu ekranu.
            Path destinationPath = Paths.get("src/main/resources/", screenshotName);

            // Wykonuje zrzut ekranu i zapisuje go jako plik PNG.
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            sourceFile.renameTo(new File(String.valueOf(destinationPath.toAbsolutePath())));

            System.out.println("Screenshot captured: " + screenshotName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

