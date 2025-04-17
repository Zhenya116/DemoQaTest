package quru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DemoQaTest {

    @BeforeAll
    static void setup() {
        Configuration.timeout = 15000; // Увеличено до 15 секунд
        Configuration.pageLoadTimeout = 30000; // Таймаут загрузки страницы 30 сек
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen = false;
    }

    @Test
    void textBoxTest() {
        try {
            // Открываем страницу с увеличенным таймаутом
            open("https://demoqa.com/automation-practice-form");

            // Основная информация
            $("#firstName").shouldBe(visible).setValue("Ivan");
            $("#lastName").setValue("Ivanov");
            $("#userEmail").setValue("test@example.com");
            $(byText("Male")).click();
            $("#userNumber").setValue("9961216614");

            // Дата рождения
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("April");
            $(".react-datepicker__year-select").selectOption("1998");
            $$(".react-datepicker__day").findBy(text("25")).click();

            // Предметы и хобби
            $("#subjectsInput").setValue("Maths").pressEnter();
            $(byText("Sports")).click();

            // Загрузка файла
            File file = new File("src/test/resources/photo.png");
            if (!file.exists()) {
                throw new RuntimeException("File not found: " + file.getAbsolutePath());
            }
            $("#uploadPicture").uploadFile(file);
            $("#uploadPicture").shouldHave(attributeMatching("value", ".*photo\\.png"));

            // Адрес
            $("#currentAddress").setValue("Almet");
            $("#currentAddress").shouldHave(value("Almet"));

            // Выбор штата и города
            $("#state").scrollTo().click();
            $(byText("NCR")).click();

            $("#city").click();
            $(byText("Delhi")).click();

            // Отправка формы
            $("#submit").click();

            // Проверки результатов
            $(".modal-content").shouldBe(visible);
            $(byText("Thanks for submitting the form")).shouldBe(visible);
            System.out.println("Nice job");

        } catch (Exception e) {
            // Делаем скриншот при ошибке
            Selenide.screenshot("error_screenshot");
            throw e;

        }
    }
}