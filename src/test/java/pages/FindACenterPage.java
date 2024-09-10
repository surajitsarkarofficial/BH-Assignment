package pages;

import com.aventstack.extentreports.Status;
import manager.ExtentReportManager;
import org.openqa.selenium.*;
import manager.WaitManager;

public class FindACenterPage extends BasePage {
    public FindACenterPage(WebDriver driver) {
        super(driver);
    }

    By LOCATION_SEARCHBOX = By.id("addressInput");
    By RESULT_COUNT = By.xpath("//div[@class='centerDetails results']/span[@class='resultsNumber']");
    By LOCATION_SEARCH_LIST = By.xpath("//div[contains(@class,'pac-container')][not(contains(@style,'display'))]/div[@class='pac-item']");
    By CENTER_RESULT_CONTAINER = By.xpath("//div[@id='center-results-container']//div[contains(@class,'centerResult')][@data-center-select-tracking]");
    By Center_RESULT_CONTAINER_TITLE = By.xpath("//div[@id='center-results-container']//div[contains(@class,'centerResult')][@data-center-select-tracking]//h3[@class='centerResult__name']");
    By CENTER_RESULT_CONTAINER_ADDRESS = By.xpath("//div[@id='center-results-container']//div[contains(@class,'centerResult')][@data-center-select-tracking]//span[@class='centerResult__address']");
    By POPUP_CENTER_TITLE = By.xpath("//*[@role='dialog'][not(@aria-hidden)]//span[@class='mapTooltip__headline']");
    By POPUP_CENTER_ADDRESS = By.xpath("//*[@role='dialog'][not(@aria-hidden)]//*[@class='mapTooltip__address']");

    public FindACenterPage searchACenter(String location) throws InterruptedException {
        char[] chars = location.toCharArray();
        for (char c : chars) {
            enterText(LOCATION_SEARCHBOX, String.valueOf(c));
            Thread.sleep(100);
        }
        WaitManager.waitForVisibilityOfElement(LOCATION_SEARCH_LIST);
        ExtentReportManager.getTest().log(Status.INFO,"Entered search location as '"+location+"' in the Search field.");
        enterText(LOCATION_SEARCHBOX, Keys.ENTER);
        ExtentReportManager.getTest().log(Status.INFO,"Pressed Enter Key.");
        return this;
    }

    public int getResultCount() {
        return Integer.parseInt(driver.findElement(RESULT_COUNT).getText());
    }

    public int getCountOFCenterResults() {
        return driver.findElements(CENTER_RESULT_CONTAINER).size();
    }

    public FindACenterPage clickOnCenterResultByIndex(int index) {
        clickOnElementByIndex(CENTER_RESULT_CONTAINER, index);
        ExtentReportManager.getTest().log(Status.INFO,"Clicked on the "+index+" indexed Center Result Container.");
        return this;
    }

    public void waitForResultToDisplay(String location) {
        String locator = "//span[contains(@class,'address')][contains(text(), '" + location + "')]";
        By address = By.xpath(locator);
        WaitManager.waitForPresenceOfAllElelments(address);
    }


    public String getResultContainerTitleByIndex(int index) {
        return getElementTextByIndex(Center_RESULT_CONTAINER_TITLE, index);
    }

    public String getResultContainerAddressByIndex(int index) {
        return getElementTextByIndex(CENTER_RESULT_CONTAINER_ADDRESS, index);
    }

    public String getPopupCenterTitle() {
        return driver.findElement(POPUP_CENTER_TITLE).getText();
    }

    public String getPopupCenterAddress() {
        String address = driver.findElement(POPUP_CENTER_ADDRESS).getText();
        String[] add = address.split("\n");
        StringBuilder sb = new StringBuilder();
        for (String addr : add) {
            sb.append(addr + " ");
        }
        return sb.toString().trim();
    }
}
