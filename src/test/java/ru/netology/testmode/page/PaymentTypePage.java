package ru.netology.testmode.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentTypePage {

    private final SelenideElement header = $("h2.heading");
    private final SelenideElement buyButton = $$(".button__text").find(exactText("Купить"));
    private final SelenideElement buyInCreditButton = $$(".button__text").find(exactText("Купить в кредит"));


    public PaymentTypePage() {
        header.shouldBe(visible);
    }

    public BuyPage clickButtonBuy() {
        buyButton.click();
        return new BuyPage();
    }

    public BuyInCreditPage clickButtonBuyInCredit() {
        buyInCreditButton.click();
        return new BuyInCreditPage();
    }


}
