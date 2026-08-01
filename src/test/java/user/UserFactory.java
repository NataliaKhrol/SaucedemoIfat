package user;

import utils.PropertyReader;

public class UserFactory {
    public static User withAdminPermission() {
        return new User(
                PropertyReader.getProperty("saucedemmo.user"),
                PropertyReader.getProperty("saucedemmo.password"));
    }

    public static User withLockedAdminPermission() {
        return new User(
                PropertyReader.getProperty("saucedemmo.locked_user"),
                PropertyReader.getProperty("saucedemmo.password"));
    }

    public static User withIncorrectPermission() {
        return new User(
                PropertyReader.getProperty("saucedemmo.incorrect_user"),
                PropertyReader.getProperty("saucedemmo.password"));
    }
}
