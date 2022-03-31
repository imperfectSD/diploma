package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;

import data.DataBaseHelper;
import data.DataHelper;
import page.PaymentPage;
import page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyWithCreditTest {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void shouldOpenApp() {
        DataBaseHelper.clean();
        open("http://localhost:8080", MainPage.class);
        mainPage.buyWithCredit();
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
    void shouldApproveCreditWithValidCard() {
        val cardNumber = DataHelper.getValidCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getRandomCardHolderName();
        val cvs = DataHelper.getValidCVS();
        paymentPage.fillOutRequiredFields(cardNumber, month, year, owner, cvs);
        paymentPage.operationApproved();
        val expected = DataHelper.getValidCardStatus();
        val actual = DataBaseHelper.getBuyWithCreditStatus();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeclineCreditWithInvalidCard() {
        val cardNumber = DataHelper.getInvalidCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getRandomCardHolderName();
        val cvs = DataHelper.getValidCVS();
        paymentPage.fillOutRequiredFields(cardNumber, month, year, owner, cvs);
        paymentPage.operationDeclined();
        val expected = DataHelper.getInvalidCardStatus();
        val actual = DataBaseHelper.getBuyWithCreditStatus();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeclineCreditWithRandomCard() {
        val cardNumber = DataHelper.getRandomCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getRandomCardHolderName();
        val cvs = DataHelper.getValidCVS();
        paymentPage.fillOutRequiredFields(cardNumber, month, year, owner, cvs);
        paymentPage.operationDeclined();
        val expected = DataHelper.getInvalidCardStatus();
        val actual = DataBaseHelper.getBuyWithCreditStatus();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeclineCreditWithEmptyCardNumber() {
        val cardNumber = DataHelper.getEmptyCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getRandomCardHolderName();
        val cvs = DataHelper.getValidCVS();
        paymentPage.fillOutRequiredFields(cardNumber, month, year, owner, cvs);
        paymentPage.invalidFormat();
    }

    @Test
    void shouldDeclineCreditWithInvalidMonth() {
        val cardNumber = DataHelper.getValidCardNumber();
        val month = DataHelper.getInvalidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getRandomCardHolderName();
        val cvs = DataHelper.getValidCVS();
        paymentPage.fillOutRequiredFields(cardNumber, month, year, owner, cvs);
        paymentPage.invalidFormat();
    }

    @Test
    void shouldDeclineCreditWithEmptyMonth() {
        val cardNumber = DataHelper.getValidCardNumber();
        val month = DataHelper.getEmptyMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getRandomCardHolderName();
        val cvs = DataHelper.getValidCVS();
        paymentPage.fillOutRequiredFields(cardNumber, month, year, owner, cvs);
        paymentPage.invalidFormat();
    }

    @Test
    void shouldDeclineCreditWithInvalidYear() {
        val cardNumber = DataHelper.getValidCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getInvalidYear();
        val owner = DataHelper.getRandomCardHolderName();
        val cvs = DataHelper.getValidCVS();
        paymentPage.fillOutRequiredFields(cardNumber, month, year, owner, cvs);
        paymentPage.invalidFormat();
    }

    @Test
    void shouldDeclineCreditWithEmptyYear() {
        val cardNumber = DataHelper.getValidCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getEmptyYear();
        val owner = DataHelper.getRandomCardHolderName();
        val cvs = DataHelper.getValidCVS();
        paymentPage.fillOutRequiredFields(cardNumber, month, year, owner, cvs);
        paymentPage.invalidFormat();
    }

    @Test
    void shouldDeclineCreditWithEmptyCardHolderName() {
        val cardNumber = DataHelper.getValidCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getEmptyCardHolderName();
        val cvs = DataHelper.getValidCVS();
        paymentPage.fillOutRequiredFields(cardNumber, month, year, owner, cvs);
        paymentPage.allFieldsShouldBeFilled();
    }

    @Test
    void shouldDeclineCreditWithInvalidCVS() {
        val cardNumber = DataHelper.getValidCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getRandomCardHolderName();
        val cvs = DataHelper.getInvalidCVS();
        paymentPage.fillOutRequiredFields(cardNumber, month, year, owner, cvs);
        paymentPage.invalidFormat();
    }

    @Test
    void shouldDeclineCreditWithEmptyCVS() {
        val cardNumber = DataHelper.getValidCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getRandomCardHolderName();
        val cvs = DataHelper.getEmptyCVS();
        paymentPage.fillOutRequiredFields(cardNumber, month, year, owner, cvs);
        paymentPage.invalidFormat();
    }

    @Test
    void shouldDeclineEmptyCreditForm() {
        val cardNumber = DataHelper.getEmptyCardNumber();
        val month = DataHelper.getEmptyMonth();
        val year = DataHelper.getEmptyYear();
        val owner = DataHelper.getEmptyCardHolderName();
        val cvs = DataHelper.getEmptyCVS();
        paymentPage.fillOutRequiredFields(cardNumber, month, year, owner, cvs);
        paymentPage.allFieldsShouldBeFilled();
    }
}
