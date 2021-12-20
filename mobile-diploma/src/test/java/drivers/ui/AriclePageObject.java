package drivers.ui;


import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE = "//*[@text='Java (programming language)']",
            FOOTER_ELEMENT = "//*[@text='View page in browser']",
            OPTIONS_BUTTON = "org.wikipedia:id/article_menu_bookmark",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "org.wikipedia:id/snackbar_action",
            ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
            MY_LIST_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.xpath(TITLE), "Cannot find article title on page", 15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
                100
        );
    }

    public void addArticleToMyList(String name_of_folder) {

        this.waitForElementAndClick(
                By.id(OPTIONS_BUTTON),
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                By.id(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find button ADD TO LIST",
                5
        );


        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Cannot find 'Got it' tip overlay ",
                5
        );

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_INPUT),
                name_of_folder,
                "Cannot put text articles folder input",
                5
        );
        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot press pk button",
                5
        );
    }

    public void addSecondArticleToMyList() {

        this.waitForElementAndClick(
                By.id(OPTIONS_BUTTON),
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                By.id(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find button ADD TO LIST",
                5
        );

        this.waitForElementAndClick(
                By.id("org.wikipedia:id/item_title"),
                "Cannot find folder title",
                5
        );
    }


    public void closeArticle() {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot close article, cannot find arrow link",
                5
        );
        this.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@index='0']"),
                "Cannot close article, cannot find arrow link",
                5
        );
    }

    public void closeArticleOneClick() {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot close article, cannot find arrow link",
                5
        );
    }

    public void assertArticleExists() {
        this.waitForElementAndClick(
                By.xpath("//*[@text='High-level programming language']"),
                "Cannot find JS High-level programming language'",
                5
        );

        this.waitForElementPresent(
                By.xpath("//*[@text='High-level programming language']"),
                "Cannot find High-level programming language title",
                5
        );
    }

    public void assertTitlePresentWithoutTimeOut() {
        this.assertElementPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find element - title"
        );
    }



}