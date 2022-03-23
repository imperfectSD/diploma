package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataBaseHelper;
import io.qameta.allure.selenide.AllureSelenide;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.DataHelper;
import page.MainPage;
import page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyWithCardTest {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void shouldOpenApp() {
        DataBaseHelper.clean();
        open("http://localhost:8080", MainPage.class);
        mainPage.buyWithCard();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void setDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldApproveBuyWithValidCard() {
        val cardNumber = DataHelper.getValidCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getRandomCardHolderName();
        val cvs = DataHelper.getValidCVS();
        paymentPage.fillOutRequiredFields(cardNumber, month, year, owner, cvs);
        paymentPage.operationApproved();
        val expected = DataHelper.getValidCardStatus();
        val actual = DataBaseHelper.getBuyWithCardStatus();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeclineBuyWithInvalidCard() {
        val cardNumber = DataHelper.getInvalidCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getRandomCardHolderName();
        val cvs = DataHelper.getValidCVS();
        paymentPage.fillOutRequiredFields(cardNumber, month, year, owner, cvs);
        paymentPage.operationDeclined();
        val expected = DataHelper.getInvalidCardStatus();
        val actual = DataBaseHelper.getBuyWithCardStatus();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeclineBuyWithRandomCard() {
        val cardNumber = DataHelper.getRandomCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getRandomCardHolderName();
        val cvs = DataHelper.getValidCVS();
        paymentPage.fillOutRequiredFields(cardNumber, month, year, owner, cvs);
        paymentPage.operationDeclined();
        val expected = DataHelper.getInvalidCardStatus();
        val actual = DataBaseHelper.getBuyWithCardStatus();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeclineBuyWithEmptyCardNumber() {
        val cardNumber = DataHelper.getEmptyCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getRandomCardHolderName();
        val cvs = DataHelper.getValidCVS();
        paymentPage.fillOutRequiredFields(cardNumber, month, year, owner, cvs);
        paymentPage.invalidFormat();
    }

    @Test
    void shouldDeclineBuyWithInvalidMonth() {
        val cardNumber = DataHelper.getValidCardNumber();
        val month = DataHelper.getInvalidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getEmptyCardHolderName();
        val cvs = DataHelper.getValidCVS();
        paymentPage.fillOutRequiredFields(cardNumber, month, year, owner, cvs);
        paymentPage.invalidFormat();
    }

    @Test
    void shouldDeclineBuyWithEmptyMonth() {
        val cardNumber = DataHelper.getValidCardNumber();
        val month = DataHelper.getEmptyMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getRandomCardHolderName();
        val cvs = DataHelper.getValidCVS();
        paymentPage.fillOutRequiredFields(cardNumber, month, year, owner, cvs);
        paymentPage.invalidFormat();
    }

    @Test
    void shouldDeclineBuyWithInvalidYear() {
        val cardNumber = DataHelper.getValidCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getInvalidYear();
        val owner = DataHelper.getRandomCardHolderName();
        val cvs = DataHelper.getValidCVS();
        paymentPage.fillOutRequiredFields(cardNumber, month, year, owner, cvs);
        paymentPage.invalidFormat();
    }

    @Test
    void shouldDeclineBuyWithEmptyYear() {
        val cardNumber = DataHelper.getValidCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getEmptyYear();
        val owner = DataHelper.getRandomCardHolderName();
        val cvs = DataHelper.getValidCVS();
        paymentPage.fillOutRequiredFields(cardNumber, month, year, owner, cvs);
        paymentPage.invalidFormat();
    }

    @Test
    void shouldDeclineBuyWithEmptyCardHolderName() {
        val cardNumber = DataHelper.getValidCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getEmptyCardHolderName();
        val cvs = DataHelper.getValidCVS();
        paymentPage.fillOutRequiredFields(cardNumber, month, year, owner, cvs);
        paymentPage.allFieldsShouldBeFilled();
    }

    @Test
    void shouldDeclineBuyWithInvalidCVS() {
        val cardNumber = DataHelper.getValidCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getRandomCardHolderName();
        val cvs = DataHelper.getInvalidCVS();
        paymentPage.fillOutRequiredFields(cardNumber, month, year, owner, cvs);
        paymentPage.invalidFormat();
    }

    @Test
    void shouldDeclineBuyWithEmptyCVS() {
        val cardNumber = DataHelper.getValidCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getRandomCardHolderName();
        val cvs = DataHelper.getEmptyCVS();
        paymentPage.fillOutRequiredFields(cardNumber, month, year, owner, cvs);
        paymentPage.invalidFormat();
    }

    @Test
    void shouldDeclineBuyWithEmptyForm() {
        val cardNumber = DataHelper.getEmptyCardNumber();
        val month = DataHelper.getEmptyMonth();
        val year = DataHelper.getEmptyYear();
        val owner = DataHelper.getRandomCardHolderName();
        val cvs = DataHelper.getEmptyCVS();
        paymentPage.fillOutRequiredFields(cardNumber, month, year, owner, cvs);
        paymentPage.allFieldsShouldBeFilled();
    }

}
