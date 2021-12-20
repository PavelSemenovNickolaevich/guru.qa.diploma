package stepic.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import stepic.helpers.AllureAttachments;
import stepic.helpers.DriverSettings;
import stepic.helpers.DriverUtils;

import static com.codeborne.selenide.Selenide.*;


@ExtendWith({AllureJunit5.class})
public class TestBase {
    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DriverSettings.configure();
    }

     public void setEnglishLanguage() {
        $(".navbar__down-arrow").click();
         $$x("//li[@class='menu-item']").get(2).shouldBe(Condition.exist);
        $$x("//li[@class='menu-item']").get(2).click();
    }

    @AfterEach
    public void addAttachments() {
        String sessionId = DriverUtils.getSessionId();

        AllureAttachments.screenshotAs("Last screenshot");
        AllureAttachments.pageSource();
        AllureAttachments.addVideo(sessionId);
//        AllureAttachments.attachNetwork(); // todo
        AllureAttachments.browserConsoleLogs();

        Selenide.closeWebDriver();
    }
}
