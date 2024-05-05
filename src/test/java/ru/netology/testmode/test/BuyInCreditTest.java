package ru.netology.testmode.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.netology.testmode.data.DataHelper;
import ru.netology.testmode.data.SQLHelper;
import ru.netology.testmode.page.PaymentTypePage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyInCreditTest {

    PaymentTypePage paymentTypePage;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        Configuration.browserCapabilities = options;

        paymentTypePage = open("http://localhost:8080/", PaymentTypePage.class);
    }

    @AfterEach
    void tearDown() {
        SQLHelper.cleanDB();
    }

    @Test
    @DisplayName("Should valid card data with approved status")
    void shouldValidDataStatusApproved() {
        var buyInCreditPage = paymentTypePage.ClickButtonBuyInCredit();
        var cardInfo = DataHelper.getValidDataWithApprovedCard();
        buyInCreditPage.enteringCardData(cardInfo);
        buyInCreditPage.getSuccessMessage();
        assertEquals("APPROVED", SQLHelper.getBuyRequestStatus());

    }

    @Test
    @DisplayName("Should valid card data with declined status")
    void shouldValidDataStatusDeclined() {
        var buyInCreditPage = paymentTypePage.ClickButtonBuyInCredit();
        var cardInfo = DataHelper.getValidDataWithDeclinedCard();
        buyInCreditPage.enteringCardData(cardInfo);
        buyInCreditPage.getErrorMessage();
        assertEquals("DECLINED", SQLHelper.getBuyRequestStatus());

    }

    @Test
    @DisplayName("Card number empty")
    void shouldCartNumberEmpty(){
        var buyInCreditPage = paymentTypePage.ClickButtonBuyInCredit();
        var cardInfo = DataHelper.getValidDataWithEmptyCard();
        buyInCreditPage.enteringCardData(cardInfo);
        buyInCreditPage.inputErrorEmptyField();
    }
    @Test
    @DisplayName("Card number with 15 digits")
    void shouldCartNumber15Digits(){
        var buyInCreditPage = paymentTypePage.ClickButtonBuyInCredit();
        var cardInfo = DataHelper.getValidDataWith15DigitCard();
        buyInCreditPage.enteringCardData(cardInfo);
        buyInCreditPage.inputErrorIncorrectFormat();
    }
    @Test
    @DisplayName("Card number with 17 digits")
    void shouldCartNumber17Digits(){
        var buyInCreditPage = paymentTypePage.ClickButtonBuyInCredit();
        var cardInfo = DataHelper.getValidDataWith17DigitCard();
        buyInCreditPage.enteringCardData(cardInfo);
        buyInCreditPage.inputErrorIncorrectFormat();
    }
    @Test
    @DisplayName("Month with 1 digit")
    void shouldMonth1Digit(){
        var buyInCreditPage = paymentTypePage.ClickButtonBuyInCredit();
        var cardInfo = DataHelper.getValidDataWith1Digit();
        buyInCreditPage.enteringCardData(cardInfo);
        buyInCreditPage.inputErrorIncorrectFormat();
    }
    @Test
    @DisplayName("Month with 00")
    void shouldMonth00() {
        var buyInCreditPage = paymentTypePage.ClickButtonBuyInCredit();
        var cardInfo = DataHelper.getValidDataWith00Month();
        buyInCreditPage.enteringCardData(cardInfo);
        buyInCreditPage.inputErrorIncorrectFormat();
    }
    @Test
    @DisplayName("Month with 13")
    void shouldMonth13() {
        var buyInCreditPage = paymentTypePage.ClickButtonBuyInCredit();
        var cardInfo = DataHelper.getValidDataWith13Month();
        buyInCreditPage.enteringCardData(cardInfo);
        buyInCreditPage.inputErrorIncorrectFormat();
    }
    @Test
    @DisplayName("Year with 1 digit")
    void shouldYear1Digit() {
        var buyInCreditPage = paymentTypePage.ClickButtonBuyInCredit();
        var cardInfo = DataHelper.getValidDataWith1DigitYear();
        buyInCreditPage.enteringCardData(cardInfo);
        buyInCreditPage.inputErrorIncorrectFormat();
    }
    @Test
    @DisplayName("Year with 23")
    void shouldYear23() {
        var buyInCreditPage = paymentTypePage.ClickButtonBuyInCredit();
        var cardInfo = DataHelper.getValidDataWith23Year();
        buyInCreditPage.enteringCardData(cardInfo);
        buyInCreditPage.inputErrorIncorrectFormat();
    }
    @Test
    @DisplayName("Year with 00")
    void shouldYear00() {
        var buyInCreditPage = paymentTypePage.ClickButtonBuyInCredit();
        var cardInfo = DataHelper.getValidDataWith00Year();
        buyInCreditPage.enteringCardData(cardInfo);
        buyInCreditPage.inputErrorIncorrectFormat();
    }
    @Test
    @DisplayName("Year empty")
    void shouldYearEmpty() {
        var buyInCreditPage = paymentTypePage.ClickButtonBuyInCredit();
        var cardInfo = DataHelper.getValidDataWithEmptyYear();
        buyInCreditPage.enteringCardData(cardInfo);
        buyInCreditPage.inputErrorEmptyField();
    }

    @Test
    @DisplayName("Owner empty")
    void shouldOwnerEmpty() {
        var buyInCreditPage = paymentTypePage.ClickButtonBuyInCredit();
        var cardInfo = DataHelper.getValidDataWithOwnerEmpty();
        buyInCreditPage.enteringCardData(cardInfo);
        buyInCreditPage.inputErrorEmptyField();
    }
    @Test
    @DisplayName("Owner text on russian")
    void shouldOwnerTextOnRussian() {
        var buyInCreditPage = paymentTypePage.ClickButtonBuyInCredit();
        var cardInfo = DataHelper.getValidDataWithOwnerTextOnRussian();
        buyInCreditPage.enteringCardData(cardInfo);
        buyInCreditPage.inputErrorIncorrectFormat();
    }
    @Test
    @DisplayName("Owner digit")
    void shouldOwnerDigit() {
        var buyInCreditPage = paymentTypePage.ClickButtonBuyInCredit();
        var cardInfo = DataHelper.getValidDataWithOwnerDigit();
        buyInCreditPage.enteringCardData(cardInfo);
        buyInCreditPage.inputErrorIncorrectFormat();
    }
    @Test
    @DisplayName("CVC/CVV empty")
    void shouldCVCEmpty() {
        var buyInCreditPage = paymentTypePage.ClickButtonBuyInCredit();
        var cardInfo = DataHelper.getValidDataWithCvcEmpty();
        buyInCreditPage.enteringCardData(cardInfo);
        buyInCreditPage.inputErrorEmptyField();
    }
    @Test
    @DisplayName("CVC/CVV 1 digit")
    void shouldCVC1Digit() {
        var buyInCreditPage = paymentTypePage.ClickButtonBuyInCredit();
        var cardInfo = DataHelper.getValidDataWith1Digit();
        buyInCreditPage.enteringCardData(cardInfo);
        buyInCreditPage.inputErrorIncorrectFormat();
    }
    @Test
    @DisplayName("CVC/CVV 4 digits")
    void shouldCVC4Digits() {
        var buyInCreditPage = paymentTypePage.ClickButtonBuyInCredit();
        var cardInfo = DataHelper.getValidDataWithCVC4Digit();
        buyInCreditPage.enteringCardData(cardInfo);
        buyInCreditPage.inputErrorIncorrectFormat();
    }
    @Test
    @DisplayName("Month empty")
    void shouldMonthEmpty() {
        var buyInCreditPage = paymentTypePage.ClickButtonBuyInCredit();
        var cardInfo = DataHelper.getValidDataWithMonthEmpty();
        buyInCreditPage.enteringCardData(cardInfo);
        buyInCreditPage.inputErrorEmptyField();
    }
    @Test
    @DisplayName("Card with 16 digits")
    void shouldCardWith16Digits() {
        var buyInCreditPage = paymentTypePage.ClickButtonBuyInCredit();
        var cardInfo = DataHelper.getValidDataWith16DigitCard();
        buyInCreditPage.enteringCardData(cardInfo);
        buyInCreditPage.getErrorMessage();
    }

}
