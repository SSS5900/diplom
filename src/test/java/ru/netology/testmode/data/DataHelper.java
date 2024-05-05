package ru.netology.testmode.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    private static final Faker faker = new Faker(new Locale("en"));
    private static final Faker fakerRu = new Faker(new Locale("ru"));

    private DataHelper() {
    }

    @Value
    public static class CardInfo {
        String number;
        String month;
        String year;
        String owner;
        String cvc;
    }

    public static String generateMonth(int number) {
        return LocalDate.now().plusMonths(number).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateYear(int number) {
        return LocalDate.now().plusYears(number).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateOwner() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String generateValidCvc() {
        return faker.numerify("###");
    }

    // Данные для позитивных сценариев
    public static String getCardNumberStatusApproved() {
        return ("4444 4444 4444 4441");
    }

    public static String getCardNumberStatusDeclined() {
        return ("4444 4444 4444 4442");
    }

    public static CardInfo getValidDataWithApprovedCard() {
        return new CardInfo(
                getCardNumberStatusApproved(),
                generateMonth(0),
                generateYear(0),
                generateOwner(),
                generateValidCvc()
        );
    }

    public static CardInfo getValidDataWithDeclinedCard() {
        return new CardInfo(
                getCardNumberStatusDeclined(),
                generateMonth(0),
                generateYear(0),
                generateOwner(),
                generateValidCvc()
        );
    }

    // Для негативных сценариев
    public static CardInfo getValidDataWithEmptyCard() {
        return new CardInfo(
                null,
                generateMonth(0),
                generateYear(0),
                generateOwner(),
                generateValidCvc()
        );
    }

    public static CardInfo getValidDataWith15DigitCard() {
        return new CardInfo(
                faker.numerify("#### #### #### ###"),
                generateMonth(0),
                generateYear(0),
                generateOwner(),
                generateValidCvc()
        );
    }

    public static CardInfo getValidDataWith17DigitCard() {
        return new CardInfo(
                faker.numerify("#### #### #### #### #"),
                generateMonth(0),
                generateYear(0),
                generateOwner(),
                generateValidCvc()
        );
    }

    public static CardInfo getValidDataWith1DigitMonth() {
        return new CardInfo(
                getCardNumberStatusApproved(),
                faker.numerify("#"),
                generateYear(0),
                generateOwner(),
                generateValidCvc()
        );
    }

    public static CardInfo getValidDataWith00Month() {
        return new CardInfo(
                getCardNumberStatusApproved(),
                "00",
                generateYear(0),
                generateOwner(),
                generateValidCvc()
        );
    }

    public static CardInfo getValidDataWith13Month() {
        return new CardInfo(
                getCardNumberStatusApproved(),
                "13",
                generateYear(0),
                generateOwner(),
                generateValidCvc()
        );
    }

    public static CardInfo getValidDataWith1DigitYear() {
        return new CardInfo(
                getCardNumberStatusApproved(),
                generateMonth(0),
                faker.numerify("#"),
                generateOwner(),
                generateValidCvc()
        );
    }

    public static CardInfo getValidDataWith23Year() {
        return new CardInfo(
                getCardNumberStatusApproved(),
                generateMonth(0),
                "23",
                generateOwner(),
                generateValidCvc()
        );
    }

    public static CardInfo getValidDataWith00Year() {
        return new CardInfo(
                getCardNumberStatusApproved(),
                generateMonth(0),
                "00",
                generateOwner(),
                generateValidCvc()
        );
    }

    public static CardInfo getValidDataWithEmptyYear() {
        return new CardInfo(
                getCardNumberStatusApproved(),
                generateMonth(0),
                null,
                generateOwner(),
                generateValidCvc()
        );
    }
    public static CardInfo getValidDataWithOwnerEmpty() {
        return new CardInfo(
                getCardNumberStatusApproved(),
                generateMonth(0),
                generateYear(0),
                null,
                generateValidCvc()
        );
    }

    public static CardInfo getValidDataWithOwnerTextOnRussian() {
        return new CardInfo(
                getCardNumberStatusApproved(),
                generateMonth(0),
                generateYear(0),
        fakerRu.name().firstName() + " " + fakerRu.name().lastName(),
                generateValidCvc()
        );
    }

    public static CardInfo getValidDataWithOwnerDigit() {
        return new CardInfo(
                getCardNumberStatusApproved(),
                generateMonth(0),
                generateYear(0),
                faker.numerify("#"),
                generateValidCvc()
        );
    }

    public static CardInfo getValidDataWithCvcEmpty() {
        return new CardInfo(
                getCardNumberStatusApproved(),
                generateMonth(0),
                generateYear(0),
                generateOwner(),
                null
        );
    }

    public static CardInfo getValidDataWith1Digit() {
        return new CardInfo(
                getCardNumberStatusApproved(),
                generateMonth(0),
                generateYear(0),
                generateOwner(),
                faker.numerify("#")
        );
    }

    public static CardInfo getValidDataWithCVC4Digit() {
        return new CardInfo(
                getCardNumberStatusApproved(),
                generateMonth(0),
                generateYear(0),
                generateOwner(),
                faker.numerify("####")
        );
    }

    public static CardInfo getValidDataWithMonthEmpty() {
        return new CardInfo(
                getCardNumberStatusApproved(),
                null,
                generateYear(0),
                generateOwner(),
                generateValidCvc()
        );
    }

    public static CardInfo getValidDataWith16DigitCard() {
        return new CardInfo(
                faker.numerify("#### #### #### ####"),
                generateMonth(0),
                generateYear(0),
                generateOwner(),
                generateValidCvc()
        );
    }
}
