package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class ActionsManager {

    public void clickOnElement(By locator)
    {
        WaitManager.waitForElementToBeClickable(locator).click();
    }
    public void clickOnElementByIndex(By locator,int index)
    {
        WaitManager.waitForPresenceOfAllElelments(locator);
        WaitManager.waitForElementToBeClickable(locator);
        DriverManager.getInstance().getDriver().findElements(locator).get(index).click();
    }

    public void enterText(By locator, String text)
    {
        WaitManager.waitForVisibilityOfElement(locator).sendKeys(text);
    }
    public void enterText(By locator, Keys key)
    {
        WaitManager.waitForVisibilityOfElement(locator).sendKeys(key);
    }

    public String getElementTextByIndex(By locator, int index)
    {
        WaitManager.waitForPresenceOfAllElelments(locator);
        return DriverManager.getInstance().getDriver().findElements(locator).get(index).getText();
    }
}
