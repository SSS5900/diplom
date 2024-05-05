package ru.netology.testmode.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.testmode.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BuyInCreditPage {


        private final SelenideElement headingBuyInCredit = $$("h3.heading").find(exactText("Кредит по данным карты"));
        private final SelenideElement number = $(".input__control[placeholder='0000 0000 0000 0000']");
        private final SelenideElement month = $(".input__control[placeholder='08']");
        private final SelenideElement year = $(".input__control[placeholder='22']");
        private final SelenideElement owner = $(".input__control:not([placeholder])");
        private final SelenideElement cvc = $(".input__control[placeholder='999']");
        private final SelenideElement continueButton = $$(".button__text").find(exactText("Продолжить"));
        private final SelenideElement inputError = $(".input__sub");
        private final SelenideElement successMessage = $(".notification_status_ok");
        private final SelenideElement errorMessage = $(".notification_status_error");


        public BuyInCreditPage() {
            headingBuyInCredit.shouldBe(visible);
        }

        public void enteringCardData(DataHelper.CardInfo cardInfo) {
            number.setValue(cardInfo.getNumber());
            month.setValue(cardInfo.getMonth());
            year.setValue(cardInfo.getYear());
            owner.setValue(cardInfo.getOwner());
            cvc.setValue(cardInfo.getCvc());
            continueButton.click();
        }

        public void getInputError(String expectedText) {
            inputError.shouldHave(exactText(expectedText));
            inputError.shouldBe(visible);

        }

        public void inputErrorEmptyField() {
            getInputError("Поле обязательно для заполнения");
        }

        public void inputErrorIncorrectFormat() {
            getInputError("Неверный формат");
        }


        public void getSuccessMessage() {
            successMessage.shouldBe(visible, Duration.ofSeconds(15));
            successMessage.$(".notification__title").shouldHave(text("Успешно"));
            successMessage.$(".notification__content").shouldHave(text("Операция одобрена Банком."));
        }

        public void getErrorMessage() {
            errorMessage.shouldBe(visible, Duration.ofSeconds(15));
            errorMessage.$(".notification__title").shouldHave(text("Ошибка"));
            errorMessage.$(".notification__content").shouldHave(text("Ошибка! Банк отказал в проведении операции."));
        }

    }
