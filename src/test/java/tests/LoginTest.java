package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    @Test
    public void correctLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        boolean titleDisplayed = driver.findElement(By.cssSelector("[data-test='title']")).isDisplayed();
        String titleName = driver.findElement(By.cssSelector("[data-test='title']")).getText();

        assertTrue(titleDisplayed);
        assertEquals(titleName, "Products");
    }

    @Test
    public void incorrectLogin() {
        loginPage.open();
        loginPage.login("Standard_user", "secret_sauce");

        boolean isErrorDisplayed = driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed();
        String errorText = driver.findElement(By.cssSelector("[data-test='error']")).getText();

        assertTrue(isErrorDisplayed);
        assertEquals(errorText, "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void lockedUserLogin() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        boolean isErrorDisplayed = driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed();
        String errorText = driver.findElement(By.cssSelector("[data-test='error']")).getText();

        assertTrue(isErrorDisplayed);
        assertEquals(errorText, "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void emptyUserLogin() {
        loginPage.open();
        loginPage.login("", "secret_sauce");

        boolean isErrorDisplayed = driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed();
        String errorText = driver.findElement(By.cssSelector("[data-test='error']")).getText();

        assertTrue(isErrorDisplayed);
        assertEquals(errorText, "Epic sadface: Username is required");
    }

    @Test
    public void emptyPasswordLogin() {
        loginPage.open();
        loginPage.login("standard_user", "");

        boolean isErrorDisplayed = driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed();
        String errorText = driver.findElement(By.cssSelector("[data-test='error']")).getText();

        assertTrue(isErrorDisplayed);
        assertEquals(errorText, "Epic sadface: Password is required");
    }
}
