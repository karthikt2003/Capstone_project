package com.fry.base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.fry.utilities.ExtentManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    public WebDriver driver;
    protected ExtentReports extent;

    @BeforeSuite
    public void setupReport() {
        extent = ExtentManager.getinstance();
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }

    @BeforeMethod
    public void setup() {
        // ✅ Setup Chrome driver
        WebDriverManager.chromedriver().setup();

        // ✅ Chrome options for better Jenkins compatibility
        ChromeOptions options = new ChromeOptions();

        // Optional: uncomment this if Jenkins sometimes blocks Chrome
        // options.addArguments("--remote-allow-origins=*");
        // options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);

        // ✅ Maximize and wait setup
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        System.out.println("✅ Chrome launched successfully!");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit(); // Close browser after each test
            System.out.println("🧹 Chrome closed after test execution.");
        }
    }

    public void navigateUrl(String url) {
        driver.get(url);
        System.out.println("🌐 Navigated to: " + url);
    }
}
