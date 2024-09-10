package pages;

import com.aventstack.extentreports.Status;
import manager.ExtentReportManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import manager.WaitManager;

public class LandingPage extends BasePage {


    public LandingPage(WebDriver driver) {
        super(driver);
    }

    By ACCEPT_ALL_COOKIES_BTN = By.id("onetrust-accept-btn-handler");
    By SEARCH_ICON = By.xpath("//a[@aria-controls='subnav-search-desktop-top']/span[contains(@class,'icon-search')]");

    By SEARCH_FIELD_INPUT_BOX = By.xpath("//nav[@id='subnav-search-desktop-top']//input[@id='search-field']");

    By SEARCH_BTN = By.xpath("//nav[@id='subnav-search-desktop-top']//button[@type='submit'][text()='Search']");

    By FIND_A_CENTER_BTN = By.xpath("//nav[contains(@class,'nav-top')]//li[contains(@class,'displayed-desktop')]/a[contains(text(),'Find a Center')]");


    public LandingPage acceptAllCookies() {
        clickOnElement(ACCEPT_ALL_COOKIES_BTN);
        ExtentReportManager.getTest().log(Status.INFO,"Cookies Accepted.");
        return this;
    }

    public LandingPage clickOnSearchIcon() {
        clickOnElement(SEARCH_ICON);
        ExtentReportManager.getTest().log(Status.INFO,"Clicked on Search Icon.");
        return this;
    }

    public boolean isSearchFieldInputBoxDisplayed() {
        WebElement element = WaitManager.waitForVisibilityOfElement(SEARCH_FIELD_INPUT_BOX);
        return element.isDisplayed();
    }

    public LandingPage typeIntoSearchField(String searchTxt) {
        enterText(SEARCH_FIELD_INPUT_BOX, searchTxt);
        ExtentReportManager.getTest().log(Status.INFO,"Entered - '"+ searchTxt+"' in the Search Field.");
        return this;
    }

    public SearchResultsPage clickSearchButton() {
        clickOnElement(SEARCH_BTN);
        ExtentReportManager.getTest().log(Status.INFO,"Clicked on Search Button.");
        return new SearchResultsPage(driver);
    }

    public FindACenterPage clickFindACenterBtn() {
        clickOnElement(FIND_A_CENTER_BTN);
        ExtentReportManager.getTest().log(Status.INFO,"Clicked on 'Find a Center' button.");
        return new FindACenterPage(driver);
    }

}
