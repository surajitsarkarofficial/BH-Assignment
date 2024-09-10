package testcases.search;

import manager.DriverManager;
import manager.ExtentReportManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

public class BaseTest {

    protected WebDriver driver;
    @BeforeSuite(alwaysRun = true)
    public void suiteSetup()
    {
        ExtentReportManager.setUpReport();
        ExtentReportManager.getReport().setSystemInfo("Suite Name : ","BHAssignment");
    }

    @BeforeTest(alwaysRun = true)
    public void testSetup(ITestContext context)
    {
        ExtentReportManager.getReport().setSystemInfo("Test Name : ",context.getName());
    }
    @BeforeMethod(alwaysRun = true)
    public void setup() {
        driver = DriverManager.getInstance().getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite()
    {
        ExtentReportManager.flushReport();
    }
}
