package page;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private SelenideElement buyButton = $("[class='button button_size_m button_theme_alfa-on-white']");
    private SelenideElement creditButton = $$("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").get(0);

    public PaymentPage buyWithCard() {
        buyButton.click();
        return new PaymentPage();
    }

    public PaymentPage buyWithCredit(){
        creditButton.click();
        return new PaymentPage();
    }
}