package stepic.helpers;

import com.github.javafaker.Faker;

public class TestData {

    static Faker faker = new Faker();

    public static String generateName = faker.name().firstName();
    public static String generateEmail = faker.internet().emailAddress();
    public static String generatePassword = faker.internet().password(8, 16);


}
