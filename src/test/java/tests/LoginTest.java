package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;

public class LoginTest extends BaseTest {
    @Test(description = "Проверка верной авторизации", priority = 1)
    public void correctLogin() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        boolean titleDisplayed = productsPage.pageIsOpen();

        assertTrue(titleDisplayed);
        assertEquals(productsPage.getNamePage(), "Products",
                "Name of the page doesn't correspond to the expected");
    }

    @DataProvider()
    public Object[][] loginData() {
        return new Object[][]{
                {withIncorrectPermission(), "Epic sadface: Username and password do not match any user in this service"},
                {withLockedAdminPermission(), "Epic sadface: Sorry, this user has been locked out."},
                {new User("", "secret_sauce"), "Epic sadface: Username is required"},
                {new User("standard_user", ""), "Epic sadface: Password is required"}
        };
    }

    @Test(priority = 2, invocationCount = 1, dataProvider = "loginData")
    public void incorrectLogin(User user, String password, String errorMsg) {
        loginPage.open();
        loginPage.login(user);

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), errorMsg);
    }
}
