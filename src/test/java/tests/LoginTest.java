package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;

@Epic("Интернет-магазин")
@Feature("Авторизация")
@Owner("Khrol Natalia bla@com.com")
public class LoginTest extends BaseTest {

    @Story("Удачная авторизация")
    @Test(description = "Проверка верной авторизации", priority = 1)
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("Saucedemo29")
    @Issue("SaucedemoIfat")
    @Description("Проверка корректной авторизации")
    public void correctLogin() {
        System.out.println("LoginTest.correctLogin running in thread: " + Thread.currentThread().getId());
        loginPage.open();
        loginPage.login(withAdminPermission());
        boolean titleDisplayed = productsPage.pageIsOpen();

        assertTrue(titleDisplayed);
        assertEquals(productsPage.getNamePage(), PRODUCTS.getDisplayName(),
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

    @Story("Неудачная авторизация")
    @Test(priority = 2, dataProvider = "loginData", dependsOnMethods = "correctLogin")
    @Severity(SeverityLevel.BLOCKER)
    public void incorrectLogin(User user, String errorMsg) {
        System.out.println("LoginTest.incorrectLogin running in thread: " + Thread.currentThread().getId());

        loginPage.open();
        loginPage.login(user);

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), errorMsg);
    }
}
