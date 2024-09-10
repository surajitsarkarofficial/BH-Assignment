package listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import manager.DriverManager;
import manager.ExtentReportManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testcases.search.BaseTest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyTestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result)
    {
        ExtentReportManager.createTest(result.getTestClass().getName().substring(result.getTestClass().getName().lastIndexOf('.')+1)+"_"+result.getMethod().getMethodName());
        ExtentReportManager.getTest().log(Status.INFO, "Test Started - "+result.getTestClass().getName().substring(result.getTestClass().getName().lastIndexOf('.')+1)+" ==> "+ result.getMethod().getMethodName());
    }
    @Override
    public void onTestFailure(ITestResult result)
    {
        String screenshotPath = System.getProperty("user.dir") + File.separator +"screenshots";
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formtter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
        String screenshotName = result.getName()+"_"+formtter.format(now);
        ExtentReportManager.getTest().log(Status.FAIL, "Test FAILED - "+result.getTestClass().getName().substring(result.getTestClass().getName().lastIndexOf('.')+1)+" ==> "+ result.getMethod().getMethodName());
        ExtentReportManager.getTest().log(Status.FAIL, "Test FAILED - "+result.getThrowable());
        File file = ((TakesScreenshot) DriverManager.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(screenshotPath + File.separator + screenshotName+".png"));
            ExtentReportManager.getTest().fail("Screenshot ", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath + File.separator + screenshotName+".png").build());
        }
        catch(IOException io)
        {
            io.printStackTrace();
        }
    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        ExtentReportManager.getTest().log(Status.PASS, "Test is successfully passed - "+result.getTestClass().getName().substring(result.getTestClass().getName().lastIndexOf('.')+1)+" ==> "+ result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result)
    {
        ExtentReportManager.createTest(result.getTestClass().getName().substring(result.getTestClass().getName().lastIndexOf('.')+1));
        ExtentReportManager.getTest().log(Status.INFO, "Test SKIPPED - "+result.getTestClass().getName().substring(result.getTestClass().getName().lastIndexOf('.')+1)+" ==> "+ result.getMethod().getMethodName());
        ExtentReportManager.getTest().log(Status.SKIP, "Test SKIPPED - "+result.getThrowable());
    }

}
