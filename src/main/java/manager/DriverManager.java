package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    private static DriverManager instance;

    private static ThreadLocal<WebDriver> tlDiver = new ThreadLocal<>();

    private DriverManager() {

    }

    private void initDriver(String browser) {
        switch (browser) {
            case "Chrome":
                tlDiver.set(new ChromeDriver());
                tlDiver.get().manage().window().maximize();
                break;

            case "Firefox":
                tlDiver.set(new FirefoxDriver());
                tlDiver.get().manage().window().maximize();
                break;

            default:
                throw new IllegalStateException("Unsupported Browser - " + browser);

        }
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        if (tlDiver.get() == null) {
            String browser = ConfigManager.getProperty("browser").toString();
            instance.initDriver(browser);
        }
        return instance;

    }

    public WebDriver getDriver() {
        return tlDiver.get();
    }

    public static void quitDriver() {
        if (tlDiver.get() != null) {
            tlDiver.get().quit();
            tlDiver.remove();
        }
    }
}
