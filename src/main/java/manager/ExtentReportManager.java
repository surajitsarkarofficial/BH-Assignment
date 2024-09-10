package manager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extentReports ;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void setUpReport()
    {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./extent-report/test-report.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
    }

    public static void createTest(String testName){
        ExtentTest extentTest = extentReports
                .createTest(testName)
                .assignDevice(ConfigManager.getProperty("browser").toString());
        test.set(extentTest);
    }

    public static ExtentTest getTest(){
        return test.get();
    }

    public static ExtentReports getReport()
    {
        return extentReports;
    }

    public static void flushReport()
    {
        extentReports.flush();
    }
}
