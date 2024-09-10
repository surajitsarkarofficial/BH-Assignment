package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import manager.WaitManager;

public class SearchResultsPage extends BasePage {

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    By SEARCH_RESULTS_TITLE = By.xpath("//div[@class='results container']/a//h3");

    public String getSearchResultTitleByIndex(int index) {
        WaitManager.waitForPresenceOfAllElelments(SEARCH_RESULTS_TITLE);
        return driver.findElements(SEARCH_RESULTS_TITLE).get(index).getText();
    }
}
