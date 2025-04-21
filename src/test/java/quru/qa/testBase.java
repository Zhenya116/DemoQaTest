package quru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import pages.RegistrationPage;

public class testBase {
    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void setup() {
        Configuration.timeout = 15000; // Увеличено до 15 секунд
        Configuration.pageLoadTimeout = 30000; // Таймаут загрузки страницы 30 сек
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen = false;
    }
}
