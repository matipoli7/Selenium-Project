package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import java.io.IOException;
import java.io.IOException;

import static io.opentelemetry.sdk.autoconfigure.AutoConfiguredOpenTelemetrySdk.initialize;

public class Reports implements ITestListener {
    public static WebDriver driver;
    private static ExtentSparkReporter sparkReporter;
    public static ExtentReports extent;
    public static ExtentTest extentTest;
    public Status pass;
    public Status fail;

    public static void konie() throws IOException {
        initialize();
        WebDriverManager.chromedriver().setup();
        extent = new ExtentReports();
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\test-output\\testReport.html");
        extent.attachReporter(sparkReporter);

        sparkReporter.config().setOfflineMode(true);
        sparkReporter.config().setDocumentTitle("Simple Automation Report");
        sparkReporter.config().setReportName("Test Report");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        sparkReporter.config().setEncoding("UTF-8");
    }

    public void inicjalize() throws IOException {
        konie();
        extentTest = extent.createTest("Find Element by Complete Text Match", "test finding element by complete text match");

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//*[starts-with(@class,'products')]/li[2]/a/h2"));

        extentTest.log(Status.PASS, "first test passed");

        extent.flush();
    }

    public void generateTestReport() throws IOException {
        extent.flush();
    }
    }

