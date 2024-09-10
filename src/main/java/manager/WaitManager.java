package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

public class WaitManager {

    public static WebElement waitForVisibilityOfElement(By locator) {
        WebDriver driver = DriverManager.getInstance().getDriver();
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static List<WebElement> waitForPresenceOfAllElelments(By locator) {
        WebDriver driver = DriverManager.getInstance().getDriver();
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public static WebElement waitForElementToBeClickable(By locator) {
        WebDriver driver = DriverManager.getInstance().getDriver();
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
