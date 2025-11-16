package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import utils.ExtentManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.time.Duration;

public class BaseTest {

    public WebDriver driver;
    public static ExtentReports extent;
    public ExtentTest test;

    @BeforeSuite
    public void startReport() {
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void setup(Method method) {
        test = extent.createTest(method.getName());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = captureScreenshot(result.getName());
            test.fail("Test Failed").addScreenCaptureFromPath(screenshotPath);
        }

        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void endReport() {
        extent.flush();
    }

    public String captureScreenshot(String name) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/reports/screenshots/" + name + ".png";

        try {
            Files.createDirectories(new File(System.getProperty("user.dir") + "/reports/screenshots/").toPath());
            Files.copy(src.toPath(), new File(path).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
}
