package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    @Test(description = "Проверка верной авторизации", priority = 1)
    public void correctLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        boolean titleDisplayed = productsPage.pageIsOpen();

        assertTrue(titleDisplayed);
        assertEquals(productsPage.getNamePage(), "Products",
                "Name of the page doesn't correspond to the expected");
    }

    @DataProvider()
    public Object[][] loginData() {
        return new Object[][]{
                {"Standard_user", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"}
        };
    }

    @Test(priority = 2, invocationCount = 1, dataProvider = "loginData")
    public void incorrectLogin(String user, String password, String errorMsg) {
        loginPage.open();
        loginPage.login(user, password);

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), errorMsg);
    }
}
