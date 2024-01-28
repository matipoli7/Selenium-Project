package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestBase {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Properties config;
    public static Properties testdata;

    public TestBase() {
        try {
            config = new Properties();
            FileInputStream configFile = new FileInputStream(System.getProperty("user.dir") +
                    "/src/main/java/config/config.properties");
            config.load(configFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            testdata = new Properties();
            FileInputStream testDataFile = new FileInputStream(System.getProperty("user.dir") +
                    "/src/main/java/testdata/testdata.properties");
            testdata.load(testDataFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialization() {
        String url = config.getProperty("URL");
        String browser = config.getProperty("browser");
        String pageLoadTimeout = config.getProperty("pageLoadTimeout");
        String windowsMaximize = config.getProperty("windowsMaximize");
        String deleteAllCookies = config.getProperty("deleteAllCookies");
        String waitTimeoutString = config.getProperty("waitTimeout");
        int waitTimeoutSecond = Integer.parseInt(waitTimeoutString);
        String grid = config.getProperty("GRID");

        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +
                        "/src/main/resources/chromedriver.exe");
                ChromeOptions chromeOptions = new ChromeOptions();
                //chromeOptions.setCapability(CapabilityType.PLATFORM_NAME,"Windows 10");
                //options.setCapability(CapabilityType.BROWSER_NAME, "Windows 10");
                if (grid.equalsIgnoreCase("false")) {
                    try {
                        driver = new RemoteWebDriver(new URL("http://172.16.1.47:4444/wd/hub"), chromeOptions);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    driver = new ChromeDriver(chromeOptions);
                }
                break;
            case "ie":
                System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") +
                        "/src/main/resources/msedgedriver.exe");
                EdgeOptions edgeOptions = new EdgeOptions();
                if (grid.equalsIgnoreCase("true")) {
                    try {
                        driver = new RemoteWebDriver(new URL("http://172.16.1.47:4444/wd/hub"), edgeOptions);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    driver = new EdgeDriver(edgeOptions);
                }
                break;
            default:
                throw new IllegalArgumentException("Nierozpoznano typu przeglądarki internetowej." +
                        "Obsługiwane następujące opcje: chrome, firefox, ie");
        }

        if (deleteAllCookies.equalsIgnoreCase("true")) {
            driver.manage().deleteAllCookies();
        }
        if (windowsMaximize.equalsIgnoreCase("true")) {
            driver.manage().window().maximize();
        }

        driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(pageLoadTimeout), TimeUnit.SECONDS);
        Duration waitTimeout = Duration.ofSeconds(waitTimeoutSecond);
        wait = new WebDriverWait(driver, waitTimeout);

        driver.get(url);

    }
}

