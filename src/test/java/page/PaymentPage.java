package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    private SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("[placeholder='08']");
    private SelenideElement yearField = $("[placeholder='22']");
    private SelenideElement ownerNameField = $$("[class='input__control']").get(3);
    private SelenideElement cvcField = $("[placeholder='999']");
    private SelenideElement continueButton = $$("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").get(1);
    private SelenideElement bankApproved = $(withText("Операция одобрена Банком."));
    private SelenideElement bankDeclined = $(withText("Ошибка! Банк отказал в проведении операции."));
    private SelenideElement formatError = $(withText("Неверный формат"));
    private SelenideElement invalidCardExpirationDate = $(withText("Неверно указан срок действия карты"));
    private SelenideElement cardExpired = $(withText("Истёк срок действия карты"));
    private SelenideElement requiredField = $(withText("Поле обязательно для заполнения"));

    public void fillOutRequiredFields(String cardNumber, String month, String year, String owner, String cvc){
        cardNumberField.setValue(cardNumber);
        monthField.setValue(month);
        yearField.setValue(year);
        ownerNameField.setValue(owner);
        cvcField.setValue(cvc);
        continueButton.click();
    }

    public void operationApproved(){
        bankApproved.should(Condition.visible, Duration.ofSeconds(15));
    }

    public void operationDeclined(){
        bankDeclined.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void invalidFormat(){
        formatError.shouldBe(Condition.visible);
    }

    public void allFieldsShouldBeFilled(){
        requiredField.shouldBe(Condition.visible);
    }

    public void invalidExpirationDate(){
        invalidCardExpirationDate.shouldBe(Condition.visible);
    }

    public void cardExpired(){
        cardExpired.shouldBe(Condition.visible);
    }

}