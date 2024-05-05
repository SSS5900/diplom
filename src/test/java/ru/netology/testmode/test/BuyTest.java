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

public class BuyTest {
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
        var buyPage = paymentTypePage.ClickButtonBuy();
        var cardInfo = DataHelper.getValidDataWithApprovedCard();
        buyPage.enteringCardData(cardInfo);
        buyPage.getSuccessMessage();
        assertEquals("APPROVED", SQLHelper.getBuyRequestStatus());

    }

    @Test
    @DisplayName("Should valid card data with declined status")
    void shouldValidDataStatusDeclined() {
        var buyPage = paymentTypePage.ClickButtonBuy();
        var cardInfo = DataHelper.getValidDataWithDeclinedCard();
        buyPage.enteringCardData(cardInfo);
        buyPage.getErrorMessage();
        assertEquals("DECLINED", SQLHelper.getBuyRequestStatus());

    }

    @Test
    @DisplayName("Card number empty")
    void shouldCartNumberEmpty(){
        var buyPage = paymentTypePage.ClickButtonBuy();
        var cardInfo = DataHelper.getValidDataWithEmptyCard();
        buyPage.enteringCardData(cardInfo);
        buyPage.inputErrorEmptyField();
    }
    @Test
    @DisplayName("Card number with 15 digits")
    void shouldCartNumber15Digits(){
        var buyPage = paymentTypePage.ClickButtonBuy();
        var cardInfo = DataHelper.getValidDataWith15DigitCard();
        buyPage.enteringCardData(cardInfo);
        buyPage.inputErrorIncorrectFormat();
    }
    @Test
    @DisplayName("Card number with 17 digits")
    void shouldCartNumber17Digits(){
        var buyPage = paymentTypePage.ClickButtonBuy();
        var cardInfo = DataHelper.getValidDataWith17DigitCard();
        buyPage.enteringCardData(cardInfo);
        buyPage.inputErrorIncorrectFormat();
    }
    @Test
    @DisplayName("Month with 1 digit")
    void shouldMonth1Digit(){
        var buyPage = paymentTypePage.ClickButtonBuy();
        var cardInfo = DataHelper.getValidDataWith1Digit();
        buyPage.enteringCardData(cardInfo);
        buyPage.inputErrorIncorrectFormat();
    }
    @Test
    @DisplayName("Month with 00")
    void shouldMonth00() {
        var buyPage = paymentTypePage.ClickButtonBuy();
        var cardInfo = DataHelper.getValidDataWith00Month();
        buyPage.enteringCardData(cardInfo);
        buyPage.inputErrorIncorrectFormat();
    }
    @Test
    @DisplayName("Month with 13")
    void shouldMonth13() {
        var buyPage = paymentTypePage.ClickButtonBuy();
        var cardInfo = DataHelper.getValidDataWith13Month();
        buyPage.enteringCardData(cardInfo);
        buyPage.inputErrorIncorrectFormat();
    }
    @Test
    @DisplayName("Year with 1 digit")
    void shouldYear1Digit() {
        var buyPage = paymentTypePage.ClickButtonBuy();
        var cardInfo = DataHelper.getValidDataWith1DigitYear();
        buyPage.enteringCardData(cardInfo);
        buyPage.inputErrorIncorrectFormat();
    }
    @Test
    @DisplayName("Year with 23")
    void shouldYear23() {
        var buyPage = paymentTypePage.ClickButtonBuy();
        var cardInfo = DataHelper.getValidDataWith23Year();
        buyPage.enteringCardData(cardInfo);
        buyPage.inputErrorIncorrectFormat();
    }
    @Test
    @DisplayName("Year with 00")
    void shouldYear00() {
        var buyPage = paymentTypePage.ClickButtonBuy();
        var cardInfo = DataHelper.getValidDataWith00Year();
        buyPage.enteringCardData(cardInfo);
        buyPage.inputErrorIncorrectFormat();
    }
    @Test
    @DisplayName("Year empty")
    void shouldYearEmpty() {
        var buyPage = paymentTypePage.ClickButtonBuy();
        var cardInfo = DataHelper.getValidDataWithEmptyYear();
        buyPage.enteringCardData(cardInfo);
        buyPage.inputErrorEmptyField();
    }

    @Test
    @DisplayName("Owner empty")
    void shouldOwnerEmpty() {
        var buyPage = paymentTypePage.ClickButtonBuy();
        var cardInfo = DataHelper.getValidDataWithOwnerEmpty();
        buyPage.enteringCardData(cardInfo);
        buyPage.inputErrorEmptyField();
    }
    @Test
    @DisplayName("Owner text on russian")
    void shouldOwnerTextOnRussian() {
        var buyPage = paymentTypePage.ClickButtonBuy();
        var cardInfo = DataHelper.getValidDataWithOwnerTextOnRussian();
        buyPage.enteringCardData(cardInfo);
        buyPage.inputErrorIncorrectFormat();
    }
    @Test
    @DisplayName("Owner digit")
    void shouldOwnerDigit() {
        var buyPage = paymentTypePage.ClickButtonBuy();
        var cardInfo = DataHelper.getValidDataWithOwnerDigit();
        buyPage.enteringCardData(cardInfo);
        buyPage.inputErrorIncorrectFormat();
    }
    @Test
    @DisplayName("CVC/CVV empty")
    void shouldCVCEmpty() {
        var buyPage = paymentTypePage.ClickButtonBuy();
        var cardInfo = DataHelper.getValidDataWithCvcEmpty();
        buyPage.enteringCardData(cardInfo);
        buyPage.inputErrorEmptyField();
    }
    @Test
    @DisplayName("CVC/CVV 1 digit")
    void shouldCVC1Digit() {
        var buyPage = paymentTypePage.ClickButtonBuy();
        var cardInfo = DataHelper.getValidDataWith1Digit();
        buyPage.enteringCardData(cardInfo);
        buyPage.inputErrorIncorrectFormat();
    }
    @Test
    @DisplayName("CVC/CVV 4 digits")
    void shouldCVC4Digits() {
        var buyPage = paymentTypePage.ClickButtonBuy();
        var cardInfo = DataHelper.getValidDataWithCVC4Digit();
        buyPage.enteringCardData(cardInfo);
        buyPage.inputErrorIncorrectFormat();
    }
    @Test
    @DisplayName("Month empty")
    void shouldMonthEmpty() {
        var buyPage = paymentTypePage.ClickButtonBuy();
        var cardInfo = DataHelper.getValidDataWithMonthEmpty();
        buyPage.enteringCardData(cardInfo);
        buyPage.inputErrorEmptyField();
    }
    @Test
    @DisplayName("Card with 16 digits")
    void shouldCardWith16Digits() {
        var buyPage = paymentTypePage.ClickButtonBuy();
        var cardInfo = DataHelper.getValidDataWith16DigitCard();
        buyPage.enteringCardData(cardInfo);
        buyPage.getErrorMessage();
    }


}
