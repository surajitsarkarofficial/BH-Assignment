package pages;

import org.openqa.selenium.WebDriver;
import manager.ActionsManager;

public class BasePage extends ActionsManager {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
