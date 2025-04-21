package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.RegistrationResultsModal;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {
    CalendarComponent calendarComponent = new CalendarComponent();
    RegistrationResultsModal registrationResultsModal = new RegistrationResultsModal();
    private final String TITLE_TEXT = "Student Registration Form";
    private SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("test@example.com"),
            numberInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput");



    public RegistrationPage openPage() {
        // Открываем страницу с увеличенным таймаутом
        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(TITLE_TEXT));

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        $("#firstName").setValue(value);

        return this;
    }

    public RegistrationPage setLastName (String value) {
        $("#lastName").setValue(value);

        return this;
    }

    public RegistrationPage setEmail (String value) {
        $("#userEmail").setValue(value);

        return this;
    }

    public RegistrationPage setGender (String value) {
        $("#genterWrapper").$(byText(value)).click();

        return this;
    }

    public RegistrationPage setPhone (String value) {
        $("#userNumber").setValue(value);

        return this;
    }

    public RegistrationPage setBirthDate (String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationPage verifyResultsModalAppears () {
        registrationResultsModal.verifyModalAppears();

        return this;
    }

    public RegistrationPage verifyResult (String key, String value) {
        registrationResultsModal.verifyResult(key, value);

        return this;
    }
}
