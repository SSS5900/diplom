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
    private final SelenideElement headingBuy = $$("h3.heading").find(exactText("Оплата по карте"));
    private final SelenideElement headingBuyInCredit = $$("h3.heading").find(exactText("Кредит по данным карты"));
    private final SelenideElement dataForm = $("[action]");


    public PaymentTypePage() {
        header.shouldBe(visible);
    }

    public BuyPage ClickButtonBuy() {
        buyButton.click();
        headingBuy.shouldBe(visible);
        dataForm.shouldBe(visible);
        return new BuyPage();
    }

    public BuyInCreditPage ClickButtonBuyInCredit() {
        buyInCreditButton.click();
        headingBuyInCredit.shouldBe(visible);
        dataForm.shouldBe(visible);
        return new BuyInCreditPage();
    }







}
