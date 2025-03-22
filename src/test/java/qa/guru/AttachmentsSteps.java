package qa.guru;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.commands.TakeScreenshot;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class AttachmentsSteps {

    public static final String REPOSITORY = "allure-framework/allure2";
    public static final String ISSUE = "2893";


    @Test
    public void testLambdaAttachmens(){
        SelenideLogger.addListener("allure", new AllureSelenide());


        step("Открываем главную страницу", () -> {
            open("https://github.com");
            attachment("Source", webdriver().driver().source());

        });


//        step("Ищем репозиторий " + REPOSITORY, () -> {
//            $(".header-search-button").click();
//            $("#query-builder-test").setValue(REPOSITORY);
//            $("#query-builder-test").submit();
//        });
//
//        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
//            $(linkText(REPOSITORY)).click();
//        });
//
//        step("Открываем таб Issues" + REPOSITORY, () -> {
//            $("#issues-tab").click();
//        });
//
//        step("Проверяем наличие Issues с номером" + ISSUE, () -> {
//            $(withText(ISSUE)).should(exist);
//        });

    }

    @Test
    public void testAnnotatedAttachmens() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.takeScreenshot();
    }
}
