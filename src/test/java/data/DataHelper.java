package data;

import com.github.javafaker.Faker;
import java.util.Locale;

public class DataHelper {

    private static Faker fakerEn = new Faker(new Locale("en"));
    private static Faker fakerRu = new Faker(new Locale("ru"));

    public static String getValidCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getValidCardStatus() {
        return "APPROVED";
    }

    public static String getInvalidCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getInvalidCardStatus() {
        return "DECLINED";
    }

    public static String getEmptyCardNumber() {
        return "                ";
    }

    public static String getRandomCardNumber() {
        return fakerEn.business().creditCardNumber();
    }

    public static String getValidMonth() {
        return "08";
    }

    public static String getInvalidMonth() {
        return "13";
    }

    public static String getEmptyMonth() {
        return "  ";
    }

    public static String getValidYear() {
        return "22";
    }

    public static String getInvalidYear() {
        return "12";
    }

    public static String getEmptyYear() {
        return " ";
    }

    public static String getRandomCardHolderName() {
        return fakerRu.name().fullName();
    }

    public static String getEmptyCardHolderName() {
        return " ";
    }

    public static String getValidCVS() {
        return "999";
    }

    public static String getInvalidCVS() {
        return "123";
    }

    public static String getEmptyCVS() {
        return "   ";
    }

}
