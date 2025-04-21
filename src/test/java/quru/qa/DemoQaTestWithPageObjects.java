package quru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DemoQaTestWithPageObjects extends testBase {

    @Test
    void textBoxTest() {
        try {
            String userName = "Alex";

            registrationPage.openPage()
                .setFirstName(userName)
                .setLastName("Ivanov")
                .setEmail("test@example.com")
                .setGender("Other")
                .setPhone("9961216614")
                    .setBirthDate("30", "July", "2008");

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

            registrationPage.verifyResultsModalAppears()
                    .verifyResult("Student Name", userName + " Ivanov")
                    .verifyResult("Student Email", "test@example.com")
                    .verifyResult("Gender", "Other")
                    .verifyResult("Mobile", "9961216614")
                    .verifyResult("Date of Birth", "30 July, 2008");

        } catch (Exception e) {
            // Делаем скриншот при ошибке
            screenshot("error_screenshot");
            throw e;
        }
    }
}