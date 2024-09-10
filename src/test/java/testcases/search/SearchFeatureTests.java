package testcases.search;

import com.aventstack.extentreports.Status;
import manager.ExtentReportManager;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.FindACenterPage;
import pages.LandingPage;
import pages.SearchResultsPage;
import manager.ConfigManager;

public class SearchFeatureTests extends BaseTest {
    LandingPage landingPage;
    FindACenterPage findACenterPage;
    String searchText = "Employee Education in 2018: Strategies to Watch";

    @BeforeMethod
    public void launchApp() {
        driver.get(ConfigManager.getProperty("url").toString());
        landingPage = new LandingPage(driver);
    }

    @Test(priority = 0)
    public void verifySearchFunctionality() throws InterruptedException {

        landingPage.acceptAllCookies().clickOnSearchIcon();
        Assert.assertTrue(landingPage.isSearchFieldInputBoxDisplayed(), "Search Field Input Box Was not displayed");
        ExtentReportManager.getTest().log(Status.PASS,"Search Field was displayed.");
        landingPage.typeIntoSearchField(searchText).clickSearchButton();
        String firstSearchResultTitle = new SearchResultsPage(driver).getSearchResultTitleByIndex(0);
        Assert.assertEquals(firstSearchResultTitle, searchText);
        ExtentReportManager.getTest().log(Status.PASS,"The Search Text - '"+searchText+"' was displayed as the first result.");

    }

    @Test(priority = 1)
    public void verifySearchCentersFunctionality() throws InterruptedException {
        findACenterPage = landingPage.acceptAllCookies().clickFindACenterBtn();
        Assert.assertTrue(driver.getCurrentUrl().contains("/child-care-locator"));
        ExtentReportManager.getTest().log(Status.PASS,"The url contains - /child-care-locator");
        findACenterPage.searchACenter("New York");
        findACenterPage.waitForResultToDisplay("New York");
        int resultCount = findACenterPage.getResultCount();
        int countOfCenters = findACenterPage.getCountOFCenterResults();
        Assert.assertEquals(resultCount, countOfCenters);
        ExtentReportManager.getTest().log(Status.PASS,"Total Result displayed is "+ resultCount + " and total centers displayed is "+ countOfCenters);
        findACenterPage.clickOnCenterResultByIndex(0);
        Assert.assertEquals(findACenterPage.getResultContainerTitleByIndex(0), findACenterPage.getPopupCenterTitle());
        ExtentReportManager.getTest().log(Status.PASS,"First Center Title is '"+findACenterPage.getResultContainerTitleByIndex(0) +" and the Title in the Popup is '"+findACenterPage.getPopupCenterTitle()+"'.");
        Assert.assertEquals(findACenterPage.getResultContainerAddressByIndex(0), findACenterPage.getPopupCenterAddress());
        ExtentReportManager.getTest().log(Status.PASS,"First Center Address is '"+findACenterPage.getResultContainerAddressByIndex(0) +" and the Address in the Popup is '"+findACenterPage.getPopupCenterAddress()+"'.");
    }

}
