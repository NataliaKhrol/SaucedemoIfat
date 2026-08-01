package tests;

import org.testng.annotations.Test;
import user.UserFactory;

import java.util.List;

import static org.testng.Assert.*;

public class CartTest extends BaseTest {
    List<String> goodsList =
            List.of("Sauce Labs Bolt T-Shirt",
                    "Sauce Labs Bike Light",
                    "Sauce Labs Fleece Jacket");

    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        for (String goodName : goodsList) {
            productsPage.addToCart(goodName);
        }
        productsPage.switchToCart();
        assertFalse(cartPage.getProductsNames().isEmpty());
        assertEquals(cartPage.getProductsNames().size(), 3);
        assertTrue(cartPage.getProductsNames().contains("Sauce Labs Fleece Jacket"));
        assertEquals(cartPage.getProductsNames(), goodsList);
    }
}
