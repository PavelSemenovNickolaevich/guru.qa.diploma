package drivers.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text,'Search Wikipedia')]",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@text='{SUBSTRING}']",
            SKIP_FIRST_PAGE = "//*[contains(@text,'SKIP')]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results']",
            SEARCH_RESULT_ELEMENT = "//*[@text='Linkin Park discography']",
            SEARCH_RESULT_ELEMENTS = "org.wikipedia:id/page_list_item_title",
            SEARCH_RESULT_WITH_TITLE_AND_DESCRIPTION = "//android.view.ViewGroup/*[@text='{SUBSTRING_TITLE}']/../*[@text='{SUBSTRING_DESCRIPTION}']";


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }


    public void skipFirstPage() {
        this.waitForElementAndClick(By.xpath(SKIP_FIRST_PAGE), "Cannot find SKIP element", 1);

    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click search button", 5);
    }


    //TEMPLATES METHODS
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }


    private static String getResultSearchElement(String substringTitle, String substringDescription) {
        return SEARCH_RESULT_WITH_TITLE_AND_DESCRIPTION.replace("{SUBSTRING_TITLE}", substringTitle).replace("{SUBSTRING_DESCRIPTION}", substringDescription);
    }


    public void waitForElementByTitleAndDescription(String title, String description) {
        String search_result_element_with_article_description = getResultSearchElement(title, description);
        this.waitForElementPresent(By.xpath(search_result_element_with_article_description), "Cannot find element with " + title + " or " + description);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring  " + substring, 10);
    }

    public int getAmountOfFoundArticles() {

        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request   ",
                15
        );
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));

//        int amount_of_search_results = MainPageObject.getAmountOfElements(
//                By.xpath("//*[@text='Linkin Park discography']")
//        );

    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Cannot find empty result element", 10);
    }

    public void assertThereIsNoResultsOfSearch() {
        this.assetElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed not find any results");
    }

    public List getTitlesList() {
        List<WebElement> titles = this.listOfElements(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Titles doesnt exist",
                10
        );
        return titles;
    }

    public void assertThereIsNoListOfResults() {
        this.waitForElementNotPresent(
                By.id(SEARCH_RESULT_ELEMENTS),
                "Titles exist!!!",
                10
        );
    }


}