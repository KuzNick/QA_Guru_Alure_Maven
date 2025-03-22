import com.codeborne.selenide.WebDriverRunner;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Ищем репозиторий ")
    public void searchForRepository(String repo) {
        $(".header-search-button").click();
        $("#query-builder-test").setValue(repo);
        $("#query-builder-test").submit();
    }

    @Step("Кликаем по ссылке репозитория ")
    public void clickOnRepositoryLink(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Открываем таб Issues")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issues с номером {issue}")
    public void shouldSeeIssueWithNumber(String issue) {
        $(withText(issue)).should(exist);
    }

//    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
//    public byte[] takeScreenshot() {
//        System.out.println("Делаем скриншот");
//        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
//    }

    @Attachment(value = "Screenshot", type = "image/jpeg", fileExtension = "jpeg")
    public byte[] takeScreenshot() {
        // Проверяем, что WebDriver запущен
        if (WebDriverRunner.hasWebDriverStarted()) {
            // Делаем скриншот и возвращаем его как массив байтов
            return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
        }
        return new byte[0]; // Возвращаем пустой массив, если WebDriver не запущен
    }
}

